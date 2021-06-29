package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.Machine;

public abstract class MachineRepository extends DomainRepositoryAbs<Machine, Long> {
    public MachineRepository() {
        super.className = "Machine";
    }
}
