package com.example.elmserver.security.JWT.hanlder;

import com.example.elmserver.entities.User;
import com.example.elmserver.security.JWT.JwtUtils;
import com.example.elmserver.security.JWT.manager.AuthenticationManager;
import com.example.elmserver.security.JWT.token.RequestAuthenticationToken;
import com.example.elmserver.security.config.SecurityContants;
import com.example.elmserver.security.events.LoginSuccessEvent;
import com.example.elmserver.utils.IpUtils;
import com.example.elmserver.utils.JsonUtils;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 登录成功事件处理器
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private final JwtUtils jwtUtils;

	private final AuthenticationManager authenticationManager;

	private final ApplicationEventPublisher applicationEventPublisher;

	public AuthenticationSuccessHandlerImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager,
			ApplicationEventPublisher applicationEventPublisher) {
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@SneakyThrows
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		//认证token
		var token = (RequestAuthenticationToken) authentication;
		User user = (User) authentication.getDetails();

		//
		String secret = UUID.randomUUID().toString();
		user.setSecret(secret);
		String jwtToken = jwtUtils.createUserToken(user, secret);//生成iwt token

		authenticationManager.login(user); //保存jwt token（管理jwt token，可以存储于服务器内存或独立redis）
		applicationEventPublisher.publishEvent(new LoginSuccessEvent(IpUtils.getIpAddr(request), user));

		// 根据请求返回的token类型返回对应的数据
		switch (token.getTokenType()) {
			case INFO:
				var simpleUser = (User) user.clone();
				simpleUser.setSecret(null);
				simpleUser.setPassword(null);
				simpleUser.setAccessToken(jwtToken);
				var usJs = JsonUtils.toJSONString(simpleUser);
				//
				response.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().write(usJs);
				break;
			default:
				response.getWriter().write(SecurityContants.TOKEN_PREFIX + jwtToken);
				break;
		}
	}

}
