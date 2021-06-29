package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.repository.WorkstationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WorkstationRepositoryImpl extends WorkstationRepository {
    @Override
    public WorkStation load(Long id) {
        return (WorkStation) getCurrentSession().load(WorkStation.class, id);
    }

    @Override
    public WorkStation get(Long id) {
        return (WorkStation) getCurrentSession().get(WorkStation.class, id);
    }
}
