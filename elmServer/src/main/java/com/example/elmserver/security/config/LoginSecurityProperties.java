package com.example.elmserver.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "business.security.login")
public class LoginSecurityProperties {

	private long defaultTimeout = 1000 * 60 * 60 * 2;

	private String secretKey = "!@Q4324zG$$=NGH#@(4982gR*&H!#(#(Q";

	private Boolean shareLogin = false;

	private Boolean loginLimit = true;

}
