package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.framgia.model.Category;
import com.framgia.model.Product;
import com.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	@Override
	public Category getParentCategory(Integer categoryId) {
		return getCategoryDAO().getParentCategory(categoryId);
	}

	@Override
	public List<Product> getProducts(Integer categoryId) {
		return getCategoryDAO().getProducts(categoryId);
	}

	@Override
	public List<Category> getChildCategories(Integer categoryId) {
		Category category = null;
		List<Category> categories = new ArrayList<Category>();
		do {
			category = getCategoryDAO().getParentCategory(categoryId);
			if (category != null) {
				categories.add(category);
				categoryId = category.getId();
			}
		} while (category != null);
		return categories;
	}

	public Category getCategory(Integer id) {
		return getCategoryDAO().findById(id);
	}

	@Override
	public Category findBy(String attribute, Serializable key) {
		return getCategoryDAO().findBy(attribute, key);
	}

	@Override
	public Category findById(Serializable key) {
		return getCategoryDAO().findById(key);
	}

	@Override
	public void delete(Category entity) {
		try {
			getCategoryDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Category entity) {
		try {
			getCategoryDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Category> getList() {
		return getCategoryDAO().getList();
	}

	@Override
	public List<Category> getList(List<Integer> keys) {
		return getCategoryDAO().getList(keys);
	}

	@Override
	public List<Category> getList(int limit) {
		return getCategoryDAO().getList(limit);
	}
}
