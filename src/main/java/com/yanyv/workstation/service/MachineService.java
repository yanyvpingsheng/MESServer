package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Machine;
import com.yanyv.workstation.entity.User;

import java.util.List;

public interface MachineService {

    void saveOrUpdate(Machine machine);

    void delete(Machine machine);

    List<Machine> queryAllLimit(int from, int length, User creater);

    Long queryNum(User creater);

    Machine get(Long id);
}
