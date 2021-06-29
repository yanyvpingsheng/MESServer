package com.yanyv.workstation.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created")
    private Long created = System.currentTimeMillis();

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;

}
