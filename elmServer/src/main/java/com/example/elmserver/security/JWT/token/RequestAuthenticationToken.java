package com.example.elmserver.security.JWT.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 自定义认证凭据
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RequestAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String userId;

    private String secret;

    /**
     * 返回客户端凭据类型
     */
    private TokenType tokenType;


    public RequestAuthenticationToken(Object principal, Object credentials, TokenType tokenType) {
        this(principal, credentials, null, null, tokenType);
    }

    public RequestAuthenticationToken(Object principal, Object credentials, final String userId, String secret) {
        this(principal, credentials, userId, secret, null);
    }


    public RequestAuthenticationToken(Object principal, Object credentials, final String userId, String secret, TokenType tokenType) {
        super(principal, credentials);
        this.userId = userId;
        this.secret = secret;
        this.tokenType = tokenType;
    }
}
