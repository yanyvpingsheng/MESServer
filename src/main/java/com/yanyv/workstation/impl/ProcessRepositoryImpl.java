package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.ProcessRepository;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessRepositoryImpl extends ProcessRepository {
    @Override
    public Process load(Long id) {
        return (Process) getCurrentSession().load(Process.class, id);
    }

    @Override
    public Process get(Long id) {
        return (Process) getCurrentSession().get(Process.class, id);
    }

    @Override
    public List<Process> queryAllLimit(int from, int length, User creater, Long workpiece) {
        if (workpiece == 0) {
            return queryAllLimit(from, length, creater);
        } else {
            Query query = getCurrentSession().createQuery("from " + className + " where creater = (from User where uid = " + creater.getUid() + ") and workpiece = (from Workpiece where id = " + workpiece + ")");
            query.setFirstResult(from);
            query.setMaxResults(length);
            return query.list();
        }
    }

    @Override
    public Long queryNum(User creater, Long workpiece) {
        if (workpiece == 0) {
            return queryNum(creater);
        } else {
            Query query = getCurrentSession().createQuery("select count(*) from " + className + " where creater = (from User where uid = " + creater.getUid() + ") and workpiece = (from Workpiece where id = " + workpiece + ")");
            return (Long) query.uniqueResult();
        }
    }
}
