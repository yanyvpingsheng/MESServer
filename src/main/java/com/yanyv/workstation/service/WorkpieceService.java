package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.entity.Workpiece;

import java.util.List;

public interface WorkpieceService {
    void save(Workpiece workpiece);

    void saveOrUpdate(Workpiece workpiece);

    void delete(Workpiece workpiece);

    Workpiece get(Long id);

    List<Workpiece> queryAllLimit(int start, int length, User creater);

    long queryNum(User creater);

    List<Workpiece> queryInWorkstation(WorkStation workStation);
}
