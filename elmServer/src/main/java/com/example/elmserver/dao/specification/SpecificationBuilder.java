package com.example.elmserver.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class SpecificationBuilder<T> {

	private Specification<T> specification;

	private SpecificationBuilder() {
	}

	public static <T> SpecificationBuilder<T> builder() {
		return new SpecificationBuilder<>();
	}

	/**
	 * 模糊查询，匹配对应字段
	 */
	public SpecificationBuilder<T> andLike(String attribute, String value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.like(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.like(attribute, value));
		}
		return this;
	}

	/**
	 * 模糊查询，匹配对应字段
	 */
	public SpecificationBuilder<T> orLike(String attribute, String value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.like(attribute, value));
		}
		else {
			this.specification = specification.or(SpecificationFactory.like(attribute, value));
		}
		return this;
	}

	/**
	 * 某字段的值等于 value 的查询条件
	 */
	public SpecificationBuilder<T> andEqual(String attribute, Object value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.equal(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.equal(attribute, value));
		}
		return this;
	}

	public SpecificationBuilder<T> andNotEqual(String attribute, Object value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.notEqual(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.notEqual(attribute, value));
		}
		return this;
	}

	/**
	 * 某字段的值等于 value 的查询条件
	 */
	public SpecificationBuilder<T> orEqual(String attribute, Object value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.equal(attribute, value));
		}
		else {
			this.specification = specification.or(SpecificationFactory.equal(attribute, value));
		}
		return this;
	}

	/**
	 * 获取对应属性的值所在区间
	 */
	public SpecificationBuilder<T> andIsBetween(String attribute, int min, int max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.and(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	public SpecificationBuilder<T> orIsBetween(String attribute, int min, int max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.or(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	public SpecificationBuilder<T> andIsBetween(String attribute, double min, double max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.and(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	public SpecificationBuilder<T> orIsBetween(String attribute, double min, double max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.or(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	public SpecificationBuilder<T> andIsBetween(String attribute, Date min, Date max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.and(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	public SpecificationBuilder<T> orIsBetween(String attribute, Date min, Date max) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.isBetween(attribute, min, max));
		}
		else {
			this.specification = specification.or(SpecificationFactory.isBetween(attribute, min, max));
		}
		return this;
	}

	/**
	 * 通过属性名和集合实现 in 查询
	 */
	public SpecificationBuilder<T> andIn(String attribute, Collection<?> c) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.in(attribute, c));
		}
		else {
			this.specification = specification.and(SpecificationFactory.in(attribute, c));
		}
		return this;
	}

	public SpecificationBuilder<T> orIn(String attribute, Collection<?> c) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.in(attribute, c));
		}
		else {
			this.specification = specification.or(SpecificationFactory.in(attribute, c));
		}
		return this;
	}

	public SpecificationBuilder<T> andGreaterThan(String attribute, Date value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.greaterThan(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.greaterThan(attribute, value));
		}
		return this;
	}

	public SpecificationBuilder<T> andLessThan(String attribute, Date value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.lessThan(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.lessThan(attribute, value));
		}
		return this;
	}

	/**
	 * 通过属性名构建大于等于 Value 的查询条件
	 */
	public SpecificationBuilder<T> andGreaterThan(String attribute, BigDecimal value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.greaterThan(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.greaterThan(attribute, value));
		}
		return this;
	}

	public SpecificationBuilder<T> orGreaterThan(String attribute, BigDecimal value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.greaterThan(attribute, value));
		}
		else {
			this.specification = specification.or(SpecificationFactory.greaterThan(attribute, value));
		}
		return this;
	}

	public SpecificationBuilder<T> andGreaterThan(String attribute, Long value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.greaterThan(attribute, value));
		}
		else {
			this.specification = specification.and(SpecificationFactory.greaterThan(attribute, value));
		}
		return this;
	}

	public SpecificationBuilder<T> orGreaterThan(String attribute, Long value) {
		if (specification == null) {
			this.specification = Specification.where(SpecificationFactory.greaterThan(attribute, value));
		}
		else {
			this.specification = specification.or(SpecificationFactory.greaterThan(attribute, value));
		}
		return this;
	}

	public Specification<T> build() {
		return specification;
	}

}
