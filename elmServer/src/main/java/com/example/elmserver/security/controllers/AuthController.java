package com.example.elmserver.security.controllers;

import com.example.elmserver.entities.dto.LoginCommand;
import com.example.elmserver.security.JWT.token.RequestAuthenticationToken;
import com.example.elmserver.security.JWT.token.TokenType;
import com.example.elmserver.security.config.SecurityContants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "认证API V1")
@RestController
@RequestMapping(SecurityContants.AuthUrl)
//
@Slf4j
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;
    @CrossOrigin
    @Operation(summary = "登录接口")
    @PostMapping("/auth-token")
    public void auth(@RequestBody @Valid LoginCommand command) throws IOException, ServletException {

        TokenType tokenType = ObjectUtils.isEmpty(command.getTokenType()) ? TokenType.INFO
                : TokenType.valueOf(command.getTokenType().toUpperCase());

        auth(command.getUsername(), command.getPassword(), tokenType);
    }

    private void auth(String username, String password, TokenType tokenType)
            throws IOException, ServletException {
        RequestAuthenticationToken token = new RequestAuthenticationToken(username, password, tokenType);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            successHandler.onAuthenticationSuccess(request, response, authentication);
        } catch (AuthenticationException e) {
            failureHandler.onAuthenticationFailure(request, response, e);
        } catch (Exception e) {
            log.error("principal[{}] login fail, reason: ", token.getPrincipal(), e);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }

}
