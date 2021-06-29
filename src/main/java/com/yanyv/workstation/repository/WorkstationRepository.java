package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.WorkStation;

public abstract class WorkstationRepository extends DomainRepositoryAbs<WorkStation, Long> {
    public WorkstationRepository() {
        super.className = "WorkStation";
    }
}
