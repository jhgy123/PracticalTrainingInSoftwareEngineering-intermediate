package com.example.elmserver.security.JWT;

import cn.hutool.core.lang.Validator;
import com.example.elmserver.entities.User;
import com.example.elmserver.security.JWT.token.RequestAuthenticationToken;
import com.example.elmserver.security.config.LoginSecurityProperties;
import com.example.elmserver.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Component
public class PasswordAuthorizationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private LoginSecurityProperties loginSecurityProperties;

    @Resource
    private UserService userSvc;


    public PasswordAuthorizationProvider(ApplicationContext applicationContext) {

    }

    @Override
    protected User retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
        RequestAuthenticationToken token = (RequestAuthenticationToken) authentication;

        return this.loadUserByUsername(username, token);
    }

    public User loadUserByUsername(String username, RequestAuthenticationToken token)
            throws UsernameNotFoundException {
        User user = null;
        boolean loginFromId = false;
        //首先尝试以id获取用户
        var id = token.getUserId();
        if (!ObjectUtils.isEmpty(id)) {
            user = userSvc.getUserById(id);
            if (user != null) {
                loginFromId = true;
            }
        }

        //尝试以username获取
        if (user == null) {
            user = userSvc.getUserByUserName(username);
        }

        //
//        if (user == null) {
//            if (Validator.isEmail(username)) {
//                user = userSvc.findUserByEmail(username);
//            } else {
//                user = userSvc.findUserByTel(username);
//            }
//        }

        if (user == null) {
            throw new BadCredentialsException("没有找到对应的用户。");
        }

        // @formatter:off
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(loginFromId ? "" : user.getPassword())//是否为通过已登录的token进行的验证。
                .enabled(user.isEnabled())
                .accountNonLocked(user.isAccountNonLocked())
                .accountNonExpired(user.isAccountNonExpired())
                .credentialsNonExpired(user.isCredentialsNonExpired()).build();
        // @formatter:on
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RequestAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) {

        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("error.platform_user.login.password");
        }

        //如果具备id，则说明是一个携带token的认证
        var token = (RequestAuthenticationToken) authentication;
        var id = token.getUserId();
        if (!ObjectUtils.isEmpty(id)) {
            return;
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("error.platform_user.login.password");
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {

        RequestAuthenticationToken result = (RequestAuthenticationToken) authentication;
        result.setDetails(user);

        return result;
    }

}
