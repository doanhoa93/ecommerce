package com.framgia.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class BaseDAOImpl<PK, T> {
  public SessionFactory sessionFactory;

  @SuppressWarnings({ "deprecation", "unchecked" })
  public T findBy(String attribute, Serializable key) {
    return (T) getSession().createCriteria(this.getClass()).add(Restrictions.eq(attribute, key))
      .uniqueResult();
  }

  public T findById(Serializable key) {
    return findBy("id", key);
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
