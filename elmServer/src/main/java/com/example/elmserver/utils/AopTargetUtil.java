package com.example.elmserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

@Slf4j
public class AopTargetUtil {

	/**
	 * 获取 目标对象
	 * @param proxy 代理对象
	 * @return
	 * @throws Exception
	 */
	public static Object getTarget(Object proxy) {
		try {
			if (!AopUtils.isAopProxy(proxy)) {
				// 不是代理对象
				return proxy;
			}

			if (AopUtils.isJdkDynamicProxy(proxy)) {
				return getTarget(getJdkDynamicProxyTargetObject(proxy));
			}
			else { // cglib
				return getTarget(getCglibProxyTargetObject(proxy));
			}

		}
		catch (Exception e) {
			log.error("check proxy object fail, ", e);
			throw new IllegalArgumentException("check proxy object fail");
		}
	}

	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

		return target;
	}

	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);

		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

		return target;
	}

}
