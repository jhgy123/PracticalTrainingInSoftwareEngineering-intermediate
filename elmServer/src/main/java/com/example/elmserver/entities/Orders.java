package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "orders_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class Orders extends AbstractDomainEntity{
    @Id//设置主键
    @Schema(description = "主键")
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    private int id;
    @ManyToOne(targetEntity = User.class)//设置对应的实体类的类型(默认User的主键作为外键）
    private User user;
    @ManyToOne(targetEntity = Business.class)//设置对应的实体类的类型(默认Business的主键作为外键）
    private Business business;
    @Column(length = 20,nullable = false)//设置字段的最大长度为20,非空
    private String orderDate;
    @Column(precision = 7,scale =2,columnDefinition="decimal(5,2) default 0.0")//设置字段数字宽度为7，小数位数为2，设置字段默认值为0.0
    private double orderTotal;
    @ManyToOne(targetEntity = Deliveryaddress.class)//设置对应的实体类的类型(默认Deliveryaddress的主键作为外键）
    private Deliveryaddress deliveryaddress;
    @Column(columnDefinition="bit default 0")//默认值0
    private Boolean OrderState;//默认值0（未支付）
}
