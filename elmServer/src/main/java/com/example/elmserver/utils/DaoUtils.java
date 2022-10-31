package com.example.elmserver.utils;

import com.example.elmserver.dao.AbstractDao;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 *
 */
public class DaoUtils {

	public static <T, ID> List<T> findByPrimaryKeyIn(AbstractDao<T, ID> dao, Collection<ID> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return new LinkedList<>();
		}
		if (ids.size() == 1) {
			for (ID id : ids) {
				if (id == null) {
					return new LinkedList<>();
				}
				T t = dao.findById(id).orElse(null);
				if (t == null) {
					return new LinkedList<>();
				}
				return Arrays.asList(t);
			}
		}
		return dao.findAllById(new HashSet<>(ids));
	}

	public static <T, ID> List<T> findByPrimaryKeyNotIn(AbstractDao<T, ID> dao, Collection<ID> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return dao.findAll();
		}
		Field field = PersistenceUtils.getPrimaryKey(dao);
		return dao.findAll(((root, cb, cq) -> cq.not(root.get(field.getName()).in(new HashSet<>(ids)))));
	}

	public static <T, ID, S extends T> List<S> safeSaveAll(AbstractDao<T, ID> dao, Collection<S> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new LinkedList<>();
		}
		return dao.saveAll(list);
	}

	public static <T, ID> void safeDeleteAll(AbstractDao<T, ID> dao, Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return;
		}
		dao.deleteAllInBatch(entities);
	}

	public static <T, ID> void deleteByPrimaryKeyIn(AbstractDao<T, ID> dao, Collection<ID> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return;
		}
		dao.deleteAllById(new HashSet<>(ids));
	}

	@SneakyThrows
	public static <T, ID> Map<ID, T> findMapByPrimaryKeyIn(AbstractDao<T, ID> dao, Collection<ID> ids) {
		List<T> list = DaoUtils.findByPrimaryKeyIn(dao, ids);
		if (CollectionUtils.isEmpty(list)) {
			return new LinkedHashMap<>();
		}
		Field field = PersistenceUtils.getPrimaryKey(dao);
		Map<ID, T> map = new HashMap<>(list.size());
		for (T t : list) {
			ID id = (ID) field.get(t);
			map.put(id, t);
		}
		return map;
	}

}
