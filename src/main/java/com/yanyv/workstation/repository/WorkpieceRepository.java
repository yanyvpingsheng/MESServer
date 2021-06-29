package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.entity.Workpiece;

import java.util.List;

public abstract class WorkpieceRepository extends DomainRepositoryAbs<Workpiece, Long> {
    public WorkpieceRepository() {
        super.className = "Workpiece";
    }

    public abstract List<Workpiece> queryInWorkstation(WorkStation workStation);
}
