package com.example.elmserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionUtils {

	/**
	 * 获取当前token对应的已登录用户
	 * @return
	 */
	public static UserDetails getCurrentLoginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	//	DaoAuthenticationProvider
		if (authentication == null) {
			return null;
		}
		if (authentication.getPrincipal() instanceof UserDetails) {
			return (UserDetails) authentication.getPrincipal();
		}
		String principal = authentication.getPrincipal().toString();
		log.trace("security context error, principal is not UserDetailsImpl. principal=[{}]", principal);
		return null;
	}

	/**
	 * 获取当前token对应的已登录用户 ID
	 * @return
	 */
	public String getCurrentLoginUserName() {
		return getCurrentLoginUser().getUsername();
	}

}
