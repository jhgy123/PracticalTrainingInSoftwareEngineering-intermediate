package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "cart_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
//类生成相对略微复杂的构建器API
//如：Student.builder()
//        .sno( "001" )
//        .sname( "admin" )
//        .sage( 18 )
//        .sphone( "110" )
//        .build();

@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class Cart extends AbstractDomainEntity{
    @Id//设置主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    @Schema(description = "主键")
    private int id;

    //外键：food_id（所属商家编号）
    @ManyToOne(targetEntity = Food.class)//设置对应的实体类的类型(默认Food的主键作为外键）
    private Food food;
    @ManyToOne(targetEntity = Business.class)//设置对应的实体类的类型(默认Business的主键作为外键）
    private Business business;
    @ManyToOne(targetEntity = User.class)//设置对应的实体类的类型(默认User的主键作为外键）
    private User user;

    @Column(nullable = false)//非空
    private int quantity;




}
