package com.yanyv.workstation.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.entity.Workpiece;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class WorkpieceVo {
    private Long id;
    // 工件名称
    private String name;
    // 工序列表
    private List<ProcessVo> processList;

    public static WorkpieceVo format(Workpiece w) {
        WorkpieceVo vo = new WorkpieceVo();
        vo.id = w.getId();
        vo.name = w.getName();
        vo.processList = ProcessVo.format(w.getProcessList());
        return vo;
    }

    public static List<WorkpieceVo> format(List<Workpiece> w) {
        List<WorkpieceVo> list = new ArrayList<>();
        for (Workpiece item : w) {
            list.add(format(item));
        }
        return list;
    }
}
