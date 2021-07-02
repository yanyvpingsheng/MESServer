package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户类
 */
@Data
@Entity
@Table(name="wuser")
public class User {

    @Id
    @GeneratedValue
    private Long uid;

    // 邮箱
    @Column(name = "email")
    private String email;

    // 用户名
    @Column(name = "name")
    private String name;

    // 密码
    @JsonIgnore
    @Column(name = "pass")
    private String pass;

    // 注册日期
    @Column(name = "date")
    private Date date;
}
