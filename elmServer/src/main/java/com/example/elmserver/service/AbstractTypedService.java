package com.example.elmserver.service;

import com.example.elmserver.dao.AbstractDao;
import com.example.elmserver.dao.specification.AbstractQueryCondition;
import com.example.elmserver.entities.AbstractDomainEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @param <T>
 * @param <IdType>
 */
public class AbstractTypedService<T extends AbstractDomainEntity, IdType>
{
    /**
     * 配套的类型化dao
     */
    protected AbstractDao<T, IdType> dataContext;


    //#region 实体类型化访问

    
    @Operation(summary = "根据id获取数据对象")
    public T getById(IdType id) {
        return dataContext.getOne(id);
    }

    
    @Operation(summary = "根据id获取数据对象, 如果没有找到则引发一个异常")
    public T getByIdNotNull(IdType id) {
        return dataContext.findById(id).orElseThrow(() -> new IllegalArgumentException("无法找到请求的数据"));
    }

    
    @Operation(summary = "删除数据对象")
    public List<T> findByIds(Collection<IdType> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new LinkedList<>();
        }
        return dataContext.findByPrimaryKeyIn(ids);
    }

    /**
     * 查询全部
     *
     * @return
     */
    
    @Operation(summary = "创建数据对象")
    public List<T> findAll() {
        return dataContext.findAll();
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Operation(summary = "根据条件查询数据对象")
    public List<T> findBy(AbstractQueryCondition condition, @Nullable Sort sort) {
        var sb = condition.builderCondition();

        if (sort == null) {
            sort = Sort.by(Sort.Direction.DESC, "createDate");
        }

        return dataContext.findAll(sb, sort);
    }

    /**
     * 分页查询数据对象
     *
     * @param pageable 分页符
     * @return
     */
    @Operation(summary = "分页查询数据对象")
    public Page<T> pageQuery(Pageable pageable) {
        return this.pageQuery(pageable, null);
    }

    /**
     * 分页查询数据对象
     *
     * @param pageable  分页符
     * @param condition 查询条件
     * @return
     */
    
    @Operation(summary = "分页查询数据对象")
    public Page<T> pageQuery(Pageable pageable, @Valid AbstractQueryCondition condition) {
        // ToDo:这个部分需要思考如何进行.
        var sb = condition.builderCondition();
        // builderCondition(condition);

        return dataContext.queryPage(pageable, sb);
    }

    
    @Operation(summary = "创建数据对象")
    public T create(T item) {
        return dataContext.save(item);
    }

    
    @Operation(summary = "批量创建数据对象")
    public List<T> batchCreate(List<T> items) {

        return dataContext.saveAll(items);
    }

    
    @Operation(summary = "更新数据对象")
    public T update(T item) {
        return dataContext.save(item);
    }

    
    @Operation(summary = "更新数据对象")
    public List<T> batchUpdate(List<T> items) {

        return dataContext.saveAll(items);
    }

    
    @Operation(summary = "删除数据对象")
    public void delete(IdType id) {
        T item = getByIdNotNull(id);
        dataContext.delete(item);
    }

    
    @Operation(summary = "删除数据对象")
    public int deleteAll(Collection<IdType> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return dataContext.deleteByIdIn(ids);
    }

    // #endregion

}
