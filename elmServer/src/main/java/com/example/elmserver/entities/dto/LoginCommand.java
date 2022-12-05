package com.example.elmserver.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录请求包装实体
 */
@Data
public class LoginCommand {

	@Schema(description = "账号")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "token类型 1. 字符串 2. Info")
	private String tokenType = "INFO";

	//以下未启用
	@Schema(description = "客户端Id，目前有BROWSER和APP可选，前者过期时间较短")
	private String clientId;

	@Schema(description = "用户域")
	private String realm;

	@Schema(description = "验证码id")
	private String captchaId;

	@Schema(description = "验证码")
	private String captchaCode;

}
