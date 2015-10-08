package com.softserve.tc.diaryclient.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.BaseDAO;

public class BaseDAOImpl<T> implements BaseDAO<T> {
    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    
    private Class<T> entity;
    
    public BaseDAOImpl() {
    }
    
    public BaseDAOImpl(Class<T> entity) {
        this.entity = entity;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
    public void
            setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    @Transactional
    public void create(T object) {
        entityManager.persist(object);
    }
    
    @Transactional
    public T find(String id) {
        return entityManager.find(entity, id);
    }
    
    @Transactional
    public void update(T object) {
        entityManager.merge(object);
        
    }
    
    @Transactional
    public void delete(T object) {
        entityManager.remove(entityManager.merge(object));
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        List<T> list = null;
        list = entityManager.createQuery("from " + entity.getCanonicalName())
                .getResultList();
        return list;
    }
    
}
