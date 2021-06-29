package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.view.Canvas;
import org.json.JSONObject;

import java.util.List;

public interface WorkstationService {
    void saveOrUpdate(WorkStation workStation);

    void delete(WorkStation workStation);

    List<WorkStation> queryAllLimit(int from, int length, User creater);

    Long queryNum(User creater);

    WorkStation get(Long id);

    JSONObject export(WorkStation workStation);

    void draw(WorkStation workStation, Canvas canvas, String mode);
}
