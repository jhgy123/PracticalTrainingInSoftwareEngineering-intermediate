package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;

@Entity//将Course类映射到数据库中
@Table(name = "Admin_Inf")//重命名表名
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
public class Admin {
    @Id//设置主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//该字段自增
    @Column(nullable = false)//重命名字段名字,非空
    @Schema(description = "主键")
    private int id;
    @Column(length = 20,nullable = false,unique = true)//重命名字段名字、设置字段的最大长度为20,非空,唯一索引
    private String name;
    @Column(length = 20,nullable = false)//重命名字段名字、设置字段的最大长度为20,非空
    private String password;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
