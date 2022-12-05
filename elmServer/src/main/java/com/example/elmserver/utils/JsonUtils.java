package com.example.elmserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json解析器
 */
public class JsonUtils {

	/**
	 * 解析对象到json字符串
	 * @param any
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJSONString(Object any) throws JsonProcessingException {
		String json = "{}";

		if (any == null) {
			return json;
		}

		ObjectMapper mapper = new ObjectMapper();

		try {
			json = mapper.writeValueAsString(any);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			// return json;
			throw e;
		}
		return json;
	}

}
