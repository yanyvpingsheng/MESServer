package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;

import java.util.List;

public abstract class ProcessRepository extends DomainRepositoryAbs<Process, Long> {
    public ProcessRepository() {
        super.className = "Process";
    }

    public abstract List<Process> queryAllLimit(int from, int length, User creater, Long workpiece);

    public abstract Long queryNum(User creater, Long workpiece);
}
