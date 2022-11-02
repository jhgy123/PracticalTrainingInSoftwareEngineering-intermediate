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
    /**
     * 根据多个id(主键)值的集合，查询数据库的多条记录
     * @param ids 查询的id(主键)集合
     * @return 实体对象列表
     */
    default List<T> findByPrimaryKeyIn(Collection<ID> ids) {
        return DaoUtils.findByPrimaryKeyIn(this, ids);
    }

    /**
     * 据多个id(主键)值的集合,查询数据库中主键不在集合中的多条记录
     * @param ids
     * @return 实体对象列表
     */
    default List<T> findByPrimaryKeyNotIn(Collection<ID> ids) {
        return DaoUtils.findByPrimaryKeyNotIn(this, ids);
    }

    /**
     *将实体对象的集合保存到数据库中，
     * 如果实体对象的id(主键）在数据库中已经存在，则对这条记录进行修改
     * 如果实体对象的id(主键）在数据库中不存在，则对这条记录进行新增
     * @param entities 要保存的实体的集合
     * @param <S> 实体的类型
     * @return 实体对象列表
     */
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

    /**
     * 根据实体对象集合(对象的id)删除所有,
     * 如果对象的id在数据库中有记录，则删除改记录
     * 如果对象的id在数据库中没有记录，则不进行操作
     * @param entities 实体对象
     */
    default void safeDeleteAll(Collection<T> entities) {
        DaoUtils.safeDeleteAll(this, entities);
    }

    /**
     * 根据id删除所有
     * @param ids id集合
     * @return
     */
    default void deleteByPrimaryKeyIn(Collection<ID> ids) {
        DaoUtils.deleteByPrimaryKeyIn(this, ids);
    }

    /**
     * 根据id查询所有，将结果映射为一个map(以id为键，对应的对象为值）
     * @param ids id集合
     * @return map(以id为键,对应的对象为值）
     */
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