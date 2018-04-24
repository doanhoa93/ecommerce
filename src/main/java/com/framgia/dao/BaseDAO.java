package com.framgia.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface BaseDAO<PK, T> {
	public T findBy(String attribute, Serializable key, boolean lock);

	public T findById(Serializable key, boolean lock);

	public List<T> getObjects();

	public List<T> getObjectsByIds(List<Integer> keys);

	List<T> getNewObjects(Date date, int limit);

	public T getFromSession(Serializable key);

	public T saveOrUpdate(T entity);

	public void delete(T entity);
}
