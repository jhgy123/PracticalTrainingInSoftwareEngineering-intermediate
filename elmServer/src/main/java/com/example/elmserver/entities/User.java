package com.example.elmserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Entity//将Course类映射到数据库中
@Table(name = "user_inf")//重命名表名
@Data //生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Builder
@AllArgsConstructor //生成包含所有字段的构造函数
@NoArgsConstructor //无参构造函数
public class User extends AbstractDomainEntity implements UserDetails {
    @Id//设置主键
    @Schema(description = "主键")
    @Column(length = 20,nullable = false)//重命名字段名字,非空
    private String id;
    @Column(length = 20,nullable = false)//设置字段的最大长度为20,非空
    private String password;
    @Column(length = 20,nullable = false,name ="name")//设置字段的最大长度为40,非空
    private String username;
    @Column(columnDefinition="bit default 1")//默认值1（男）
    private Boolean sex;
//头像，存储的是图片
    private byte[] img;
    @Column(columnDefinition="bit default 1")//默认值1（正常）
    private  Boolean delTag;
    @Schema(description = "账户是否可用")
    @Column()
    private boolean enabled = true;


    @Schema(description = "凭据是否未过期")
    @Column()
    private boolean credentialsNonExpired = true;

    @Schema(description = "账号是否未锁定")
    @Column()
    private boolean accountNonLocked = true;

    @Schema(description = "账户是否未过期")
    @Column()
    private boolean accountNonExpired = true;

    @Schema(description = "密码过期时间，密码有效时间")
    @Column()
    private Date credentialsExpiredDate;

    @Schema(description = "账号过期时间（账号有效时间）")
    @Column()
    private Date accountExpiredDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + username + '\'' +
                ", sex=" + sex +
                ", img=" + Arrays.toString(img) +
                ", delTag=" + delTag +
                '}';
    }


    //
    @JsonIgnore
    @Transient
    private String secret;
    //
    //@JsonIgnore
    @Transient
    private String accessToken;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Transient
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

}
