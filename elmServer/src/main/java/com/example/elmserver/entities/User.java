package com.example.elmserver.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Entity//将Course类映射到数据库中
@Table(name = "user_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class User extends AbstractDomainEntity{
    @Id//设置主键
    @Schema(description = "主键")
    @Column(length = 20,nullable = false)//重命名字段名字,非空
    private String id;
    @Column(length = 20,nullable = false)//设置字段的最大长度为20,非空
    private String password;
    @Column(length = 20,nullable = false)//设置字段的最大长度为40,非空
    private String name;
    @Column(columnDefinition="bit default 1")//默认值1（男）
    private Boolean sex;
//头像，存储的是图片
    private byte[] img;
    @Column(columnDefinition="bit default 1")//默认值1（正常）
    private  Boolean delTag;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", img=" + Arrays.toString(img) +
                ", delTag=" + delTag +
                '}';
    }
}
