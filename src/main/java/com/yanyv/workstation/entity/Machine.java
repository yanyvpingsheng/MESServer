package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

/**
 * 机器实体类
 */
@Data
@Entity
@Table(name="wmachine")
public class Machine {
    @Id
    @GeneratedValue
    private Long id;
    // 机器名称
    @Column(name = "name")
    private String name;
    // 能力列表 能加工的工序
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machine", fetch = FetchType.EAGER)
    private List<Process> ability;

    @ManyToOne()
    @JoinColumn(name = "uid")
    private User creater;
}
