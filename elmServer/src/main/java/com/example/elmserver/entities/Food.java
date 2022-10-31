package com.example.elmserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "Food_Inf")//重命名表名
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
public class Food extends AbstractDomainEntity{
    @Id//设置主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    private int foodId;

    @Column(length = 30,nullable = false)//重命名字段名字、设置字段的最大长度为30,非空
    private String foodName;

    @Column(length = 30)//重命名字段名字、设置字段的最大长度为30
    private String foodExplain;

    @Column(precision = 5,scale =2,nullable = false,columnDefinition="decimal(5,2) default 0.0")//重命名字段名字、设置字段数字宽度为5，小数位数为2，非空,默认值0.0
    private double foodPrice;

    //外键：bussiness_id（所属商家编号）
    @ManyToOne(targetEntity = Business.class)//设置对应的实体类的类型(默认Business的主键作为外键）
//    @JoinColumn(name = "bussinessid")//重新设置外键的名称
    private Business business;

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodExplain='" + foodExplain + '\'' +
                ", foodPrice=" + foodPrice +
                ", business=" + business +
                '}';
    }
}
