package com.example.elmserver.security.config;

import com.example.elmserver.security.JWT.PasswordAuthorizationProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 全局
 */
@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	@Resource
	private PasswordAuthorizationProvider passwordAuthorizationProvider;


	/**
	 * spring.security 配置段
	 */
	@Resource
	private SecurityProperties securityProperties;

	@Resource
	private PasswordEncoder passwordEncoder;

	@Override
	public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		// 测试用内设账号提供程序
		// var testAuth = testAuthenticationProvider();

		authenticationManagerBuilder.authenticationProvider(passwordAuthorizationProvider)
				//.authenticationProvider(userDetailsAuthenticationProvider)
		// .authenticationProvider(testAuth)
		;
	}


}
