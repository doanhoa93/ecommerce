package com.framgia.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.model.User;

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

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public T getFromSession(Serializable key) {
		if (key == null)
			return null;
		return getSession().get(typeClass, key);
	}

	public Criteria createEntityCriteria() {
		return getSession().createCriteria(typeClass);
	}

	public Criteria createEntityCriteriawithAlias(String alias) {
		return getSession().createCriteria(typeClass, alias);
	}

	@SuppressWarnings("unchecked")
	public List<T> getObjects(int off, int limit) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(off);
		criteria.setMaxResults(limit);
		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getNewObjects(Date date, int limit) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("createdAt", date));
		if (limit != 0)
			criteria.setMaxResults(limit);

		criteria.addOrder(Order.desc("createdAt"));
		return criteria.list();
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> getObjects() {
		return (List<T>) createEntityCriteria().list();
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> getObjectsByIds(List<Integer> keys) {
		return (List<T>) createEntityCriteria().add(Restrictions.in("id", keys)).list();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByIdsWithOrder(List<Integer> keys) {
		List<Product> products = createEntityCriteria().add(Restrictions.in("id", keys)).list();
		Product temp = null;
		for (int i = 0; i < keys.size(); i++)
			for (int j = 0; j < products.size(); j++)
				if (products.get(j).getId() == keys.get(i)) {
					temp = products.get(i);
					products.set(i, products.get(j));
					products.set(j, temp);
					break;
				}
		return products;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoriesByIdsWithOrder(List<Integer> keys) {
		List<Category> categories = createEntityCriteria().add(Restrictions.in("id", keys)).list();
		Category temp = null;
		for (int i = 0; i < keys.size(); i++)
			for (int j = 0; j < categories.size(); j++)
				if (categories.get(j).getId() == keys.get(i)) {
					temp = categories.get(i);
					categories.set(i, categories.get(j));
					categories.set(j, temp);
					break;
				}
		return categories;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByIdsWithOrder(List<Integer> keys) {
		List<User> users = getSession().createCriteria(User.class).add(Restrictions.in("id", keys))
		    .list();
		User temp = null;
		for (int i = 0; i < keys.size(); i++)
			for (int j = 0; j < users.size(); j++)
				if (users.get(j).getId() == keys.get(i)) {
					temp = users.get(i);
					users.set(i, users.get(j));
					users.set(j, temp);
					break;
				}
		return users;
	}
}
