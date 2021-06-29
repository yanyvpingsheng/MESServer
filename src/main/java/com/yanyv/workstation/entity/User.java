package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="wuser")
public class User {

    @Id
    @GeneratedValue
    private Long uid;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "pass")
    private String pass;

    @Column(name = "date")
    private Date date;
}
