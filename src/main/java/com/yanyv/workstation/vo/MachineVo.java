package com.yanyv.workstation.vo;

import com.yanyv.workstation.entity.Machine;
import com.yanyv.workstation.entity.Process;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class MachineVo {
    private Long id;
    // 机器名称
    private String name;
    // 能力列表 能加工的工序
    private List<ProcessVo> ability;

    public static MachineVo format(Machine m) {
        MachineVo vo = new MachineVo();
        vo.id = m.getId();
        vo.name = m.getName();
        vo.ability = ProcessVo.format(m.getAbility());
        return vo;
    }

    public static List<MachineVo> format(List<Machine> m) {
        List<MachineVo> list = new ArrayList<>();
        for (Machine item : m) {
            list.add(format(item));
        }
        return list;
    }
}
