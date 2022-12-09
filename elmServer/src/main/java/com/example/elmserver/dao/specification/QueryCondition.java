package com.example.elmserver.dao.specification;
import lombok.Data;
/**
 * 查询条件表达式
 *
 * @apiNote 目前对创建的 与,或,非,包含 进行检测
 */
@Data
public class QueryCondition {

	private String property;

	private Object value;

	private String condition;

}
