package com.example.elmserver.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ConfigurationProperties(prefix = "business.security")
public class SecurityProperties {

	private List<CorsProperties> cors = Collections.singletonList(new CorsProperties());

	private List<IgnorePath> ignoreTokenUrls = new LinkedList<>();

	public List<CorsProperties> getCors() {
		return cors;
	}

	public void setCors(List<CorsProperties> cors) {
		this.cors = cors;
	}

	public List<IgnorePath> getIgnoreTokenUrls() {
		return ignoreTokenUrls;
	}

	public void setIgnoreTokenUrls(List<IgnorePath> ignoreTokenUrls) {
		this.ignoreTokenUrls = ignoreTokenUrls;
	}

	/**
	 * 跨域配置
	 */
	public static class CorsProperties {

		private String path = "/**";

		private Boolean allowCredentials = true;

		private String exposedHeaders = "*";

		private List<String> allowedOrigins = Collections.singletonList("*");

		private List<HttpMethod> allowedMethods = Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT,
			HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.PATCH, HttpMethod.TRACE);

		private List<String> allowedHeaders = Collections.singletonList("*");

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public List<String> getAllowedOrigins() {
			return allowedOrigins;
		}

		public void setAllowedOrigins(List<String> allowedOrigins) {
			this.allowedOrigins = allowedOrigins;
		}

		public List<HttpMethod> getAllowedMethods() {
			return allowedMethods;
		}

		public void setAllowedMethods(List<HttpMethod> allowedMethods) {
			this.allowedMethods = allowedMethods;
		}

		public List<String> getAllowedHeaders() {
			return allowedHeaders;
		}

		public void setAllowedHeaders(List<String> allowedHeaders) {
			this.allowedHeaders = allowedHeaders;
		}

		public Boolean getAllowCredentials() {
			return allowCredentials;
		}

		public void setAllowCredentials(Boolean allowCredentials) {
			this.allowCredentials = allowCredentials;
		}

		public String getExposedHeaders() {
			return exposedHeaders;
		}

		public void setExposedHeaders(String exposedHeaders) {
			this.exposedHeaders = exposedHeaders;
		}

	}

	/**
	 * 忽略路径
	 */
	public static class IgnorePath {

		private String path;

		private HttpMethod method;

		public IgnorePath() {
		}

		public IgnorePath(String path, HttpMethod method) {
			this.path = path;
			this.method = method;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public HttpMethod getMethod() {
			return method;
		}

		public void setMethod(HttpMethod method) {
			this.method = method;
		}

	}

}
