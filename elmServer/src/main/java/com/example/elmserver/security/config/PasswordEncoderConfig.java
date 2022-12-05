package com.example.elmserver.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 支持的密码配置
 */
@Configuration
public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		String idForEncode = Encoder.MD5.getCode();
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(Encoder.MD4.getCode(), new Md4PasswordEncoder());
		encoders.put(Encoder.MD5.getCode(), new MessageDigestPasswordEncoder(Encoder.MD5.getCode()));
		encoders.put(Encoder.MD5.getCode().toLowerCase(), new MessageDigestPasswordEncoder(Encoder.MD5.getCode()));
		encoders.put(Encoder.NOOP.getCode(), NoOpPasswordEncoder.getInstance());
		encoders.put(Encoder.PBKDF2.getCode(), new Pbkdf2PasswordEncoder());
		encoders.put(Encoder.SCRYPT.getCode(), new SCryptPasswordEncoder());
		encoders.put(Encoder.SHA_1.getCode(), new MessageDigestPasswordEncoder(Encoder.SHA_1.getCode()));
		encoders.put(Encoder.SHA256.getCode(), new StandardPasswordEncoder());
		encoders.put(Encoder.SHA_256.getCode(), new MessageDigestPasswordEncoder(Encoder.SHA_256.getCode()));
		encoders.put(Encoder.BCRYPT.getCode(), new BCryptPasswordEncoder());
		encoders.put(Encoder.ARGON_2.getCode(), new Argon2PasswordEncoder());
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
		return passwordEncoder;
	}

}

/**
 * @see PasswordEncoder
 */
enum Encoder {

	/**
	 * bcrypt
	 */
	BCRYPT("bcrypt"),
	/**
	 * pbkdf2
	 */
	PBKDF2("pbkdf2"),
	/**
	 * scrypt
	 */
	SCRYPT("scrypt"),
	/**
	 * SHA-1
	 */
	SHA_1("SHA-1"),
	/**
	 * sha256
	 */
	SHA256("sha256"),
	/**
	 * SHA-256
	 */
	SHA_256("SHA-256"),
	/**
	 * MD4
	 */
	MD4("MD4"),
	/**
	 * MD5
	 */
	MD5("MD5"),
	/**
	 * noop
	 */
	NOOP("noop"),
	/**
	 * argon2
	 */
	ARGON_2("argon2");

	private final String code;

	Encoder(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}