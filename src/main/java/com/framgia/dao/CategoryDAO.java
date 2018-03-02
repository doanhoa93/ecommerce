package com.framgia.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	Category getParentCategory(Integer categoryId);

	List<Category> hotCategories(int limit);

	List<Category> getCategories(int off, int limit, Order order);
}
