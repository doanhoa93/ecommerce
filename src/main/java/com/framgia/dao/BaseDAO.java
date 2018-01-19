package com.framgia.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<PK, T> {
	public T findBy(String attribute, Serializable key);

	public T findById(Serializable key);

	public List<T> getList();

	public List<T> getList(List<Integer> keys);

	public List<T> getList(int limit);

	public void delete(T entity);

	public void saveOrUpdate(T entity);
}
