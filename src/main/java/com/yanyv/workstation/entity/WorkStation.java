package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作中心
 */
@Data
@Entity
@Table(name="wworkstation")
public class WorkStation {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "station_piece",
            joinColumns = {@JoinColumn(name = "station_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "piece_id", referencedColumnName = "id")})
    private List<Workpiece> workpieceList;
    // 根据制造工件自动生成所需机器
    @Transient
    private List<Machine> machineList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "uid")
    private User creater;

    // 从工件列表获取机器列表
    public void init() {
        for (Workpiece workpiece : workpieceList) {
            for (Process process : workpiece.getProcessList()) {
                if (!machineList.contains(process.getMachine())) {
                    machineList.add(process.getMachine());
                }
            }
        }
    }
}
