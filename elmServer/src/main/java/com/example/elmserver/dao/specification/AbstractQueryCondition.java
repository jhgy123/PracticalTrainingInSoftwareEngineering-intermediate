package com.example.elmserver.dao.specification;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
/**
 * 查询条件
 */
public class AbstractQueryCondition {

	List<QueryCondition> _lst;

	private List<QueryCondition> getCondition() {
		return null;
	}

	/**
	 * 将条件转换为默认的查询表达式
	 * @return
	 */
	public Specification builderCondition() {
		var sb = SpecificationBuilder.builder();
		return sb.build();
	}

}
