package com.framgia.dao;

import com.framgia.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	Category getParentCategory(Integer categoryId);
}
