package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.WorkStation;
import com.yanyv.workstation.entity.Workpiece;
import com.yanyv.workstation.repository.WorkpieceRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkpieceRepositoryImpl extends WorkpieceRepository {

    @Override
    public Workpiece load(Long id) {
        return (Workpiece) getCurrentSession().load(Workpiece.class, id);
    }

    @Override
    public Workpiece get(Long id) {
        return (Workpiece) getCurrentSession().get(Workpiece.class, id);
    }

    @Override
    public List<Workpiece> queryInWorkstation(WorkStation workStation) {
        Session session = getCurrentSession();
        Query query = session.
                createQuery("select p from WorkStation s join s.workpieceList p where s.id = " + workStation.getId());
        return query.list();
    }
}
