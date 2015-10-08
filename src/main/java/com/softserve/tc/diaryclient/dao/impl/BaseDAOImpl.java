package com.softserve.tc.diaryclient.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
   public EntityManager getEntityManager() {
        return entityManager;
    }

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Transactional
	public void create(T object) {
		entityManager.merge(object);
	}

	@Transactional
	public T findByNickName(String nickName) {
		T element = null;
		Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		element = (T) entityManager
				.createQuery("from " + persistentClass.getSimpleName() + " where nick_name= '" + nickName + "'")
				.getSingleResult();
		return element;
	}

	@Transactional
	public void update(T object) {
		entityManager.merge(object);

	}

	@Transactional
	public void delete(String nickName) {
		entityManager.createQuery("delete from " + entity.getCanonicalName() + " where nick_name= '" + nickName + "'")
				.executeUpdate();
	}

	@Transactional
	public List<T> getAll() {
		List<T> list = null;
		list = entityManager.createQuery("from " + entity.getCanonicalName()).getResultList();
		return list;
	}

}
