package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.*;
import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.heredity.DNA;
import com.yanyv.workstation.heredity.Heredity;
import com.yanyv.workstation.heredity.RRNA;
import com.yanyv.workstation.repository.WorkstationRepository;
import com.yanyv.workstation.util.Gantt;
import com.yanyv.workstation.view.Canvas;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkstationServiceImpl implements WorkstationService{
    @Autowired
    private WorkstationRepository repository;


    @Override
    public void saveOrUpdate(WorkStation workStation) {
        repository.saveOrUpdate(workStation);
    }

    @Override
    public void delete(WorkStation workStation) {
        repository.delete(workStation);
    }

    @Override
    public List<WorkStation> queryAllLimit(int from, int length, User creater) {
        return repository.queryAllLimit(from, length, creater);
    }

    @Override
    public Long queryNum(User creater) {
        return repository.queryNum(creater);
    }

    @Override
    public WorkStation get(Long id) {
        return repository.get(id);
    }

    @Override
    public JSONObject export(WorkStation workStation) {
        workStation = repository.get(workStation.getId());
        workStation.init();
        JSONObject object = new JSONObject();
        // 工件集合
        JSONArray workpieceArray = new JSONArray();
        // 工件阵
        JSONArray processArray = new JSONArray();
        // 工件加工时间阵
        JSONArray processTimeArray = new JSONArray();
        // 工件机器需求阵
        JSONArray processMachineArray = new JSONArray();
        for (Workpiece workpiece : workStation.getWorkpieceList()) {
            workpieceArray.put(workpiece.getName());
            JSONArray nameArray = new JSONArray();
            JSONArray timeArray = new JSONArray();
            JSONArray machineArray = new JSONArray();
            for (Process process : workpiece.getProcessList()) {
                nameArray.put(process.getName());
                timeArray.put(process.getTime());
                machineArray.put(process.getMachine().getName());
            }
            processArray.put(nameArray);
            processTimeArray.put(timeArray);
            processMachineArray.put(machineArray);
        }
        // 机器集合
        JSONArray machineArray = new JSONArray();
        // 机器能力集合
        JSONArray machineAbilityArray = new JSONArray();
        for (Machine machine : workStation.getMachineList()) {
            machineArray.put(machine.getName());
            JSONArray itemArray = new JSONArray();
            for (Process process : machine.getAbility()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("工件", process.getWorkpiece().getName());
                jsonObject.put("工序", process.getWorkpiece().getProcessList().indexOf(process) + 1);
                itemArray.put(jsonObject);
            }
            machineAbilityArray.put(itemArray);
        }
        object.put("工件集合", workpieceArray);
        object.put("工件阵", processArray);
        object.put("工件加工时间阵", processTimeArray);
        object.put("工件机器需求阵", processMachineArray);
        object.put("机器集合", machineArray);
        object.put("机器能力集合", machineAbilityArray);
        return object;
    }

    @Override
    public void draw(WorkStation workStation, Canvas canvas, String mode) {
        List<Gantt.GanttValue> vList = new ArrayList<>();
        if (mode.equals("work")) {
            // 遍历车间模型中的工件列表
            for (Workpiece wp : workStation.getWorkpieceList()) {
                // 声明甘特图行数据
                Gantt.GanttValue value = new Gantt.GanttValue();
                // 传入工序列表 格式化
                value.formatToGanttValue(wp.getProcessList(), mode);
                // 将数据行加入到数据列中
                vList.add(value);
            }
        } else if (mode.equals("machine")) {
            // 遍历车间模型中的机器列表
            for (Machine machine : workStation.getMachineList()) {
                // 声明甘特图行数据
                Gantt.GanttValue value = new Gantt.GanttValue();
                // 获取机器能力列表
                List list = machine.getAbility();
                // 按时间排序
                Collections.sort(list);
                // 传入工序列表 格式化
                value.formatToGanttValue(list, mode);
                // 将数据行加入到数据列中
                vList.add(value);
            }
        }
        // 画甘特图，传入容器和数据
        Gantt.drawGantt(canvas, vList);
    }
}
