package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;

import java.util.List;

public interface ProcessService {
    void saveOrUpdate(Process process);

    void delete(Process process);

    List<Process> queryAllLimit(int from, int length, User creater, Long workpiece);

    Long queryNum(User creater, Long workpiece);
}
