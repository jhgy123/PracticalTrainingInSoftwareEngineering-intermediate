package com.example.elmserver.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;

public final class SpecificationFactory<T> {

	/**
	 * 模糊查询，匹配对应字段
	 */
	public static <T> Specification<T> like(String attribute, String value) {
		return (root, query, cb) -> cb.like(root.get(attribute), value);
	}

	/**
	 * 某字段的值等于 value 的查询条件
	 */
	public static <T> Specification<T> equal(String attribute, Object value) {
		return (root, query, cb) -> cb.equal(root.get(attribute), value);
	}

	public static <T> Specification<T> notEqual(String attribute, Object value) {
		return (root, query, cb) -> cb.notEqual(root.get(attribute), value);
	}

	/**
	 * 获取对应属性的值所在区间
	 */
	public static <T> Specification<T> isBetween(String attribute, Comparable min, Comparable max) {
		return (root, query, cb) -> cb.between(root.get(attribute), min, max);
	}

	public static <T> Specification<T> isNull(String attribute) {
		return (root, query, criteriaBuilder) -> root.get(attribute).isNull();
	}

	public static <T> Specification<T> isNotNull(String attribute) {
		return (root, query, criteriaBuilder) -> root.get(attribute).isNotNull();
	}

	public static <T> Specification<T> isMember(String attribute, T value) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isMember(value, root.get(attribute));
	}

	public static <T> Specification<T> isNotMember(String attribute, T value) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isNotMember(value, root.get(attribute));
	}

	/**
	 * 通过属性名和集合实现 in 查询
	 */
	public static <T> Specification<T> in(String attribute, Collection<?> c) {
		return (root, query, cb) -> root.get(attribute).in(c);
	}

	public static <T> Specification<T> in(SingularAttribute<T, ?> attribute, Collection<?> c) {
		return (root, query, cb) -> root.get(attribute).in(c);
	}

	public static <T> Specification<T> notIn(String attribute, Collection<?> c) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.not(root.get(attribute).in(c));
	}

	public static <T> Specification<T> notIn(SingularAttribute<T, ?> attribute, Collection<?> c) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.not(root.get(attribute).in(c));
	}

	public static <T> Specification<T> greaterThan(String attribute, Comparable value) {
		return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
	}

	public static <T, C> Specification<T> greaterThan(SingularAttribute<T, Comparable> attribute, Comparable value) {
		return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
	}

	public static <T> Specification<T> greaterThanOrEqual(String attribute, Comparable value) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), value);
	}

	public static <T> Specification<T> greaterThanOrEqual(SingularAttribute<T, Comparable> attribute,
			Comparable value) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), value);
	}

	public static <T> Specification<T> lessThan(String attribute, Comparable value) {
		return (root, query, cb) -> cb.lessThan(root.get(attribute), value);
	}

	public static <T> Specification<T> lessThan(SingularAttribute<T, Comparable> attribute, Comparable value) {
		return (root, query, cb) -> cb.lessThan(root.get(attribute), value);
	}

	public static <T> Specification<T> lessThanOrEqual(String attribute, Comparable value) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
	}

	public static <T> Specification<T> lessThanOrEqual(SingularAttribute<T, Comparable> attribute, Comparable value) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
	}

	public static <T> Specification<T> isTrue(String attribute) {
		return (root, query, cb) -> cb.isTrue(root.get(attribute));
	}

	public static <T> Specification<T> isTrue(SingularAttribute<T, Boolean> attribute) {
		return (root, query, cb) -> cb.isTrue(root.get(attribute));
	}

	public static <T> Specification<T> isFalse(String attribute) {
		return (root, query, cb) -> cb.isFalse(root.get(attribute));
	}

	public static <T> Specification<T> isFalse(SingularAttribute<T, Boolean> attribute) {
		return (root, query, cb) -> cb.isFalse(root.get(attribute));
	}

	public static <T> Specification<T> builder() {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
	}

}
