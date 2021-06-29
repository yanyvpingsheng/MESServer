package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Machine;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.MachineRepository;
import com.yanyv.workstation.repository.WorkpieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineRepository repository;

    @Override
    public void saveOrUpdate(Machine machine) {
        repository.saveOrUpdate(machine);
    }

    @Override
    public void delete(Machine machine) {
        repository.delete(machine);
    }

    @Override
    public List<Machine> queryAllLimit(int from, int length, User creater) {
        return repository.queryAllLimit(from, length, creater);
    }

    @Override
    public Long queryNum(User creater) {
        return repository.queryNum(creater);
    }

    @Override
    public Machine get(Long id) {
        return repository.get(id);
    }
}
