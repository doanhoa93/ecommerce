package com.framgia.dao;

import java.util.List;

import com.framgia.model.Category;
import com.framgia.model.Product;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	Category getParentCategory(Integer categoryId);

	List<Product> getProducts(Integer categoryId);
}
