package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "admin_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class Admin extends AbstractDomainEntity{
    @Id//设置主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    @Schema(description = "主键")
    private int id;
    @Column(length = 20,nullable = false,unique = true)//重命名字段名字、设置字段的最大长度为20,非空,唯一索引
    private String name;
    @Column(length = 20,nullable = false)//设置字段的最大长度为20,非空
    private String password;
    @Column(length = 400)//设置字段的最大长度为200,非空
    private String remarks;
    @Lob
    @Column(columnDefinition="TEXT")
    private String img; //头像，存储的是图片

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
