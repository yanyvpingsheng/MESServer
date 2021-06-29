package com.yanyv.workstation.vo;

import com.yanyv.workstation.entity.Machine;
import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.entity.Workpiece;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProcessVo {
    private Long id;
    // 工序名称
    private String name;
    // 工序开始时间
    private int start;
    // 工序加工时间
    private int time;
    // 工序加工机器
    private Machine machine;
    // 所属工件
    private Workpiece workpiece;

    public static ProcessVo format(Process p) {
        ProcessVo vo = new ProcessVo();
        vo.id = p.getId();
        vo.name = p.getName();
        vo.time = p.getTime();
        vo.machine = new Machine();
        vo.machine.setId(p.getMachine().getId());
        vo.machine.setName(p.getMachine().getName());
        vo.workpiece = new Workpiece();
        vo.workpiece.setId(p.getWorkpiece().getId());
        vo.workpiece.setName(p.getWorkpiece().getName());
        return vo;
    }

    public static List<ProcessVo> format(List<Process> p) {
        List<ProcessVo> list = new ArrayList<>();
        for (Process item : p) {
            list.add(format(item));
        }
        return list;
    }

}
