package com.example.elmserver.security.JWT.token;



public enum TokenType  {

	/**
	 * string
	 */
	STRING(1, "字符串"),


	/**
	 * json
	 */
	INFO(3, "INFO"),;

	private final Integer id;

	private final String name;

	TokenType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}


}
