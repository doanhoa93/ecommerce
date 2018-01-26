package com.framgia.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class BaseDAOAbstract<PK extends Serializable, T> extends HibernateDaoSupport {

	private Class<T> typeClass;

	public Class<T> getTypeClass() {
		return typeClass;
	}

	public BaseDAOAbstract(Class<T> typeClass) {
		this.typeClass = typeClass;
	}

	@SuppressWarnings("unchecked")
	public BaseDAOAbstract() {
		this.typeClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
		        .getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public T findBy(String attribute, Serializable key, boolean lock) {
		Criteria criteria = createEntityCriteria();
		if (lock)
			criteria.setLockMode(LockMode.UPGRADE);
		return (T) criteria.add(Restrictions.eq(attribute, key)).uniqueResult();
	}

	public T findById(Serializable key) {
		return findBy("id", key, true);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public Criteria createEntityCriteria() {
		return getSession().createCriteria(typeClass);
	}

	public Criteria createEntityCriteriawithAlias(String alias) {
		return getSession().createCriteria(typeClass, alias);
	}

	@SuppressWarnings("unchecked")
	public List<T> getObjects(int limit) {
		Criteria criteria = createEntityCriteria();
		criteria.setMaxResults(limit);
		return (List<T>) criteria.list();
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> getObjects() {
		return (List<T>) createEntityCriteria().list();
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> getObjectsByIds(List<Integer> keys) {
		return (List<T>) createEntityCriteria().add(Restrictions.in("id", keys)).list();
	}
}
