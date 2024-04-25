package org.alireza.base.service;

import org.alireza.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    T saveOrUpdate(T entity);

    T findById(ID id);

    void deleteById(ID id);

    List<T> findAll();
}
