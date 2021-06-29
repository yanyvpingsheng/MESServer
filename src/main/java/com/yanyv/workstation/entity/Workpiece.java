package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 工件类
@Data
@Entity
@Table(name="wworkpiece")
public class Workpiece {
    @Id
    @GeneratedValue
    private Long id;
    // 工件名称
    @Column(name = "name")
    private String name;
    // 工序列表
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workpiece", fetch = FetchType.EAGER)
    private List<Process> processList;
    // 上道工序完成时间
    @Transient
    private int lastTime;

    @ManyToOne()
    @JoinColumn(name = "uid")
    private User creater;

}
