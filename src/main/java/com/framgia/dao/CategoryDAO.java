package com.framgia.dao;

import java.util.List;

import com.framgia.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	Category getParentCategory(Integer categoryId);

	List<Category> hotCategories(int limit);
}
