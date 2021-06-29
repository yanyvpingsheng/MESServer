package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.Code;
import com.yanyv.workstation.entity.Person;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.CodeRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CodeRepositoryImpl extends CodeRepository {

    @Override
    public Code load(Long id) {
        return (Code)getCurrentSession().load(Code.class,id);
    }

    @Override
    public Code get(Long id) {
        return (Code)getCurrentSession().get(Code.class,id);
    }

    @Override
    public Long save(Code entity) {
        entity.setDate(new Date());
        return super.save(entity);
    }

    @Override
    public void saveOrUpdate(Code entity) {
        entity.setDate(new Date());
        super.saveOrUpdate(entity);
    }

    @Override
    public Code queryByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Code where email='" + email + "'");
        List<Code> list = query.list();
        if (list.size() != 0)
            return list.get(0);
        else
            return null;
    }
}
