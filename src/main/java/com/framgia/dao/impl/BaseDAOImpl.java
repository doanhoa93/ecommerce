package com.framgia.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAOImpl<PK, T> {
	@Autowired
	private SessionFactory sessionFactory;

	final Class<T> typeClass;

	public BaseDAOImpl(Class<T> typeClass) {
		this.typeClass = typeClass;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public T findBy(String attribute, Serializable key) {
		return (T) getSession().createCriteria(typeClass).add(Restrictions.eq(attribute, key)).uniqueResult();
	}

	public T findById(Serializable key) {
		return findBy("id", key);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> getList() {
		return (List<T>) getSession().createCriteria(typeClass).list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> getList(List<Integer> keys) {
		return (List<T>) getSession().createCriteria(typeClass).add(Restrictions.in("id", keys)).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<T> getList(int limit) {
		Criteria criteria = getSession().createCriteria(typeClass);
		criteria.setMaxResults(limit);
		return (List<T>) criteria.list();
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}
}
