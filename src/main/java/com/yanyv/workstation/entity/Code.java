package com.yanyv.workstation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="wcode")
public class Code {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private int code;

    @Column(name = "date")
    private Date date;

}
