package com.example.elmserver.security.JWT;

import com.example.elmserver.entities.User;
import com.example.elmserver.security.JWT.manager.AuthenticationManager;
import com.example.elmserver.security.JWT.token.RequestAuthenticationToken;
import com.example.elmserver.security.config.SecurityContants;
import com.example.elmserver.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //是否为携带jwt token
        final String authToken = getAuthToken(request);
        if (ObjectUtils.isEmpty(authToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        //是否为白名单
        Boolean isWhiteRequest = securityProperties.getIgnoreTokenUrls().stream().anyMatch(pattern -> {
            AntPathRequestMatcher getRequestMatcher = new AntPathRequestMatcher(pattern.getPath(),
                    pattern.getMethod().name());
            return getRequestMatcher.matches(request);
        });
        if (isWhiteRequest) {
            filterChain.doFilter(request, response);
            return;
        }

        //提取token信息
        try {
            Map<String, Object> map = jwtUtils.parseToken(authToken);
            String secret = map.getOrDefault(JwtUtils.KEY_RAND, null).toString();
            User userDetails = jwtUtils.parseUser(map);
            String userName = userDetails.getUsername();
            final String userId = userDetails.getId().toString();

            //
            if (!authenticationManager.validate(userDetails)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "the user is not logged in");
                log.trace("the user[userId={},userName={}] is not logged in", userId, userName);
                return;
            }

            //
            RequestAuthenticationToken token = new RequestAuthenticationToken(userDetails.getUsername(), "", userId, secret);
            token.setDetails(userDetails);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (Throwable e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
            log.trace("The token is not valid, reason: {}", e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthToken(HttpServletRequest request) {
        String authToken = null;
        String authorization = request.getHeader(SecurityContants.HEADER_STRING);
        if (StringUtils.hasText(authorization) && authorization.startsWith(SecurityContants.TOKEN_PREFIX)) {
            authToken = authorization.substring(7);
        }
        return authToken;
    }

}
