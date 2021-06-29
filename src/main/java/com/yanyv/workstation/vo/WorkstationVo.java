package com.yanyv.workstation.vo;

import com.yanyv.workstation.entity.WorkStation;
import lombok.Data;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkstationVo {
    private Long id;
    // 车间名称
    private String name;
    // 加工列表
    private List<WorkpieceVo> workpieceList;

    public static WorkstationVo format(WorkStation w) {
        WorkstationVo vo = new WorkstationVo();
        vo.id = w.getId();
        vo.name = w.getName();
        vo.workpieceList = WorkpieceVo.format(w.getWorkpieceList());
        return vo;
    }

    public static List<WorkstationVo> format(List<WorkStation> w) {
        List<WorkstationVo> list = new ArrayList<>();
        for (WorkStation item : w) {
            list.add(format(item));
        }
        return list;
    }
}
