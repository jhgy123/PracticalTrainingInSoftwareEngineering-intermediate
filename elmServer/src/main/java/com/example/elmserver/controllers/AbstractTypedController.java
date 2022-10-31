package com.example.elmserver.controllers;

import com.example.elmserver.dao.specification.AbstractQueryCondition;
import com.example.elmserver.entities.AbstractDomainEntity;
import com.example.elmserver.service.AbstractTypedService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
public class AbstractTypedController<T extends AbstractDomainEntity, IdType> {

    /**
     * 学生服务
     */
    // @Resource
    protected AbstractTypedService<T, IdType> svcContext;

    //#region 关联的数据实体

    @Operation(summary = "查询 单个实体")
    @GetMapping("/{id}")
    public T queryById(@PathVariable IdType id) {
        return svcContext.getByIdNotNull(id);
    }

    @GetMapping("/all")
    @Operation(summary = "查询 全部实体")
    public List<T> findAll() {
        return svcContext.findAll();
    }

    @GetMapping("/by")
    @Operation(summary = "查询 符合条件的所有实体")
    public List<T> findBy(@ModelAttribute AbstractQueryCondition condition, @Nullable Sort sort) {
        return svcContext.findBy(condition, sort);
    }

    /**
     * @param pageable 分页
     * @return
     */
    @Operation(summary = "查询 分页")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<T> pageQuery(Pageable pageable) {
        return svcContext.pageQuery(pageable);
    }

    /**
     * 根据条件分页查询对象
     *
     * @param condition 查询条件
     * @param pageable  分页
     * @return
     */
    public Page<T> pageQueryBy(@ModelAttribute AbstractQueryCondition condition, Pageable pageable) {
        return svcContext.pageQuery(pageable, condition);
    }

    //#endregion

    //#region 关联的数据实体基本操作

    @PostMapping
    @Operation(summary = "创建 数据实体")
    public T create(@RequestBody T item) {
        return svcContext.create(item);
    }

    @PutMapping
    @Operation(summary = "修改 数据实体")
    public T update(@RequestBody T item) {
        return svcContext.update(item);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除 数据实体")
    public ResponseEntity<Void> delete(@PathVariable IdType id) {
        svcContext.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量更新")
    public ResponseEntity<Void> batchCreate(@RequestBody List<T> items) {
        var res = svcContext.batchCreate(items);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新")
    public ResponseEntity<Void> batchUpdate(@RequestBody List<T> items) {
        var res = svcContext.batchUpdate(items);
        var ids = res.stream().map(r -> r.getEntityId()).toArray();

        log.debug("items: {}", ids.length);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除")
    public ResponseEntity<Integer> batchDelete(@RequestParam List<IdType> ids) {
        var res = svcContext.deleteAll(ids);
        return new ResponseEntity<Integer>(res, HttpStatus.OK);
    }

    //#endregion

}
