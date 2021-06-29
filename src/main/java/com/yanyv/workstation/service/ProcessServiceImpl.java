package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.ProcessRepository;
import com.yanyv.workstation.repository.WorkpieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService{

    @Autowired
    private ProcessRepository repository;

    @Override
    public void saveOrUpdate(Process process) {
        repository.saveOrUpdate(process);
    }

    @Override
    public void delete(Process process) {
        repository.delete(process);
    }

    @Override
    public List<Process> queryAllLimit(int from, int length, User creater, Long workpiece) {
        return repository.queryAllLimit(from, length, creater, workpiece);
    }

    @Override
    public Long queryNum(User creater, Long workpiece) {
        return repository.queryNum(creater, workpiece);
    }
}
