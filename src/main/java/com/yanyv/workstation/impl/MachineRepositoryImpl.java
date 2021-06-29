package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.Machine;
import com.yanyv.workstation.repository.MachineRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MachineRepositoryImpl extends MachineRepository {
    @Override
    public Machine load(Long id) {
        return (Machine) getCurrentSession().load(Machine.class, id);
    }

    @Override
    public Machine get(Long id) {
        return (Machine) getCurrentSession().get(Machine.class, id);
    }
}
