package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.entity.Workpiece;
import com.yanyv.workstation.repository.WorkpieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkpieceServiceImpl implements WorkpieceService{

    @Autowired
    private WorkpieceRepository repository;

    @Override
    public void save(Workpiece workpiece) {
        repository.save(workpiece);
    }

    @Override
    public void saveOrUpdate(Workpiece workpiece) {
        repository.saveOrUpdate(workpiece);
    }

    @Override
    public void delete(Workpiece workpiece) {
        repository.delete(workpiece);
    }

    @Override
    public Workpiece get(Long id) {
        return repository.get(id);
    }

    @Override
    public List<Workpiece> queryAllLimit(int start, int length, User creater) {
        return repository.queryAllLimit(start, length, creater);
    }

    @Override
    public long queryNum(User creater) {
        return repository.queryNum(creater);
    }

    @Override
    public List<Workpiece> queryInWorkstation(WorkStation workStation) {
        return repository.queryInWorkstation(workStation);
    }
}
