package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.User;

import java.io.Serializable;
import java.util.List;

public interface DomainRepository<T,PK extends Serializable> {
    T load(PK id);

    T get(PK id);

    List<T> findAll();

    void persist(T entity);

    PK save(T entity);

    void saveOrUpdate(T entity);

    void delete(T t);

    void flush();

    List<T> queryAllLimit(int start, int length, User creater);

    Long queryNum(User creater);
}
