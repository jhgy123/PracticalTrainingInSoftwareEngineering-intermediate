package com.example.elmserver.security.JWT.manager;

import com.example.elmserver.entities.User;
import com.example.elmserver.security.config.LoginSecurityProperties;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用于管理jwt凭证的认证管理器，可以实现为内存或redis
 */
public abstract class AbstractAuthenticationManager implements AuthenticationManager {

    protected static final String STORE_NAME = "login";

    @Resource
    protected LoginSecurityProperties loginSecurityProperties;

    @Override
    public void login(User userDetails) {
        String userName = userDetails.getUsername();
        String userId = userDetails.getId().toString();
        String secret = userDetails.getSecret();
        loginInner(generateKey(userName, userId, secret), secret);

    }

    /**
     * 实际登录方法
     *
     * @param key   唯一用户标识
     * @param value 用户标识对应的随机密钥
     */
    protected abstract void loginInner(String key, String value);

    @Override
    public boolean validate(User userDetails) {
        String userName = userDetails.getUsername();
        String userId = userDetails.getId().toString();
        String secret = userDetails.getSecret();
        return validateInner(generateKey(userName, userId, secret), secret);
    }

    @Override
    public void logout(User userDetails) {
        String userName = userDetails.getUsername();
        String userId = userDetails.getId().toString();
        String secret = userDetails.getSecret();
        logoutInner(generateKey(userName, userId, secret));
    }

    @Override
    public void logoutAll(User userDetails) {
        String userName = userDetails.getUsername();
        String userId = userDetails.getId().toString();
        getAllKey(userName, userId).forEach(this::logoutInner);
    }

    /**
     * 实际退出方法
     *
     * @param key 唯一用户标识
     */
    protected abstract void logoutInner(String key);

    /**
     * 验证
     *
     * @param key   唯一用户标识
     * @param secret 密钥
     * @return 是否登录
     */
    protected abstract boolean validateInner(String key, String secret);

    protected final String generateKey(String userName, String userId, String secret) {

        if (loginSecurityProperties.getShareLogin()) {
            return String.format("%s:%s:%s:%s", STORE_NAME, userName, userId, secret);
        } else {
            return String.format("%s:%s:%s", STORE_NAME, userName, userId);
        }
    }

    /**
     * 获取一个用户登录在某一类型客户端上的所有key信息
     *
     * @param userName
     * @param userId
     * @return
     */
    protected abstract List<String> getAllKey(String userName, String userId);

}
