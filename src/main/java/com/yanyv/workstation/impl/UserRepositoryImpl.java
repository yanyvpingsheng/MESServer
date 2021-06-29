package com.yanyv.workstation.impl;

import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.UserRepository;
import org.hibernate.*;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Repository
public class UserRepositoryImpl extends UserRepository {
    @Override
    public User load(Long id) {
        return (User) getCurrentSession().load(User.class, id);
    }

    @Override
    public User get(Long id) {
        return (User) getCurrentSession().get(User.class, id);
    }

    @Override
    public Long save(User entity) {
        entity.setDate(new Date());
        return super.save(entity);
    }

    @Override
    public User queryByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from User where email='" + email + "'");
        List<User> list = query.list();
        if (list.size() != 0)
            return list.get(0);
        else
            return null;
    }
}
