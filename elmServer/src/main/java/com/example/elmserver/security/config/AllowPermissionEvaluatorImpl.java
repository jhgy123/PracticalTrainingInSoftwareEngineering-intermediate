package com.example.elmserver.security.config;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * PermissionEvaluator的默认实现，全部许可通行，避免bean缺失导致应用无法访问
 */
public class AllowPermissionEvaluatorImpl implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return true;
	}

}
