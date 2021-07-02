package com.yanyv.workstation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 验证码实体类
 */
// 该注解自动生成get/set等方法
@Data
@Entity
@Table(name="wcode")
public class Code {
    /**
     * 代理主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 邮箱地址
     */
    @Column(name = "email")
    private String email;

    /**
     * 验证码
     */
    @Column(name = "code")
    private int code;

    /**
     * 发送时间
     */
    @Column(name = "date")
    private Date date;

}
