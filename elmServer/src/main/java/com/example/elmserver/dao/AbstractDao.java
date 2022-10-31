package com.example.elmserver.dao;

import com.example.elmserver.dao.specification.SpecificationBuilder;
import com.example.elmserver.utils.DaoUtils;
import com.sun.istack.Nullable;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 抽象dao
 *
 * @param <T>
 */
@NoRepositoryBean
public interface AbstractDao<T, ID>
        extends JpaRepository<T, ID>
        , JpaSpecificationExecutor<T> {

    default List<T> findByPrimaryKeyIn(Collection<ID> ids) {
        return DaoUtils.findByPrimaryKeyIn(this, ids);
    }

    default List<T> findByPrimaryKeyNotIn(Collection<ID> ids) {
        return DaoUtils.findByPrimaryKeyNotIn(this, ids);
    }

    default <S extends T> List<S> safeSaveAll(Collection<S> entities) {
        return DaoUtils.safeSaveAll(this, entities);
    }

    /**
     * 根据id删除所有
     *
     * @param ids id集合
     * @return 删除的数量
     */
    int deleteByIdIn(Collection<ID> ids);

    default void safeDeleteAll(Collection<T> entities) {
        DaoUtils.safeDeleteAll(this, entities);
    }

    default void deleteByPrimaryKeyIn(Collection<ID> ids) {
        DaoUtils.deleteByPrimaryKeyIn(this, ids);
    }

    @SneakyThrows
    default Map<ID, T> findMapByPrimaryKeyIn(Collection<ID> ids) {
        return DaoUtils.findMapByPrimaryKeyIn(this, ids);
    }

    /**
     * @param pageable
     * @return
     */
    default Page<T> queryPage(Pageable pageable, @Nullable Specification<T> spec) {
        if (spec == null) {
            spec = (Specification<T>) SpecificationBuilder.builder().build();
        }

        return this.findAll(spec, pageable);
    }
}