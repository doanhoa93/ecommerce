package com.framgia.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface BaseDAO<PK, T> {
	public T findBy(String attribute, Serializable key, boolean lock);

	public T findById(Serializable key);

	public List<T> getObjects();

	public List<T> getObjectsByIds(List<Integer> keys);

	public List<T> getObjects(int off, int limit);

	List<T> getNewObjects(Date date, int limit);

	public void delete(T entity);

	public T saveOrUpdate(T entity);

	public T getFromSession(Serializable key);
}
