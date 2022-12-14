package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "deliveryaddress_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class Deliveryaddress extends AbstractDomainEntity{
    @Id//设置主键
    @Schema(description = "主键")
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    private int id;
    @Column(length = 20,nullable = false)//设置字段的最大长度为40,非空
    private String contactName;
    private Boolean contactSex;//默认值1（男）
    @Column(length = 20,nullable = false)//设置字段的最大长度为40,非空
    private String contactTel;
    @Column(length = 100,nullable = false)//设置字段的最大长度为40,非空
    private String address;
    //外键：user_id（所属商家编号）
    @ManyToOne(targetEntity = User.class)//设置对应的实体类的类型(默认User的主键作为外键）
    private User user;
}
