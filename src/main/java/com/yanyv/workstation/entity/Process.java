package com.yanyv.workstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanyv.workstation.util.Gantt;
import lombok.Data;

import javax.persistence.*;

// 工序类 实现甘特图格式接口，实现排序接口
@Data
@Entity
@Table(name="wprocess")
public class Process implements Gantt.GanttFormat, Comparable<Process> {
    @Id
    @GeneratedValue
    private Long id;
    // 工序名称
    @Column(name = "name")
    private String name;
    // 工序开始时间
    @Transient
    private int start;
    // 工序加工时间
    @Column(name = "time")
    private int time;
    // 工序加工机器
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    // 所属工件
    @ManyToOne
    @JoinColumn(name = "workpiece_id")
    private Workpiece workpiece;

    @ManyToOne()
    @JoinColumn(name = "uid")
    private User creater;


    // 获取甘特图-行-项id
    @Override
    public String getGanttId(String mode) {
        if (mode.equals("work")) {
            // 如果是作业模式
            // 返回机器名称
            return machine.getName();
        } else if (mode.equals("machine")) {
            // 如果是机器模式
            // 返回工件名称
            return workpiece.getName();
        }
        // 其他情况返回空字符串
        return "";
    }

    // 获取甘特图-行名称
    @Override
    public String getLineName(String mode) {
        if (mode.equals("work")) {
            // 如果是作业模式
            // 返回工件名称
            return workpiece.getName();
        } else if (mode.equals("machine")) {
            // 如果是机器模式
            // 返回机器名称
            return machine.getName();
        }
        // 其他情况返回空字符串
        return "";
    }

    // 获取甘特图-行-项开始位置
    @Override
    public int getGanttStart() {
        return start;
    }

    // 获取甘特图-行-项长度
    @Override
    public int getGanttLength() {
        return time;
    }

    // 获取甘特图-行-项名称 返回 工件名称-工序名称
    @Override
    public String getGanttName() {
        return workpiece.getName() + "-" + name;
    }



    // 按工序开始时间排序
    @Override
    public int compareTo(Process process) {
        if (start > process.start) {
            return 1;
        } else {
            return -1;
        }
    }
}
