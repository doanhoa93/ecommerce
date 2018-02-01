package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.framgia.model.Category;
import com.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	@Override
	public Category getParentCategory(Integer categoryId) {
		return getCategoryDAO().getParentCategory(categoryId);
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
	public Category findBy(String attribute, Serializable key, boolean lock) {
		return getCategoryDAO().findBy(attribute, key, lock);
	}

	@Override
	public Category findById(Serializable key) {
		return getCategoryDAO().findById(key);
	}

	@Override
	public boolean delete(Category entity) {
		try {
			getCategoryDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Category entity) {
		try {
			getCategoryDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Category> getObjects() {
		return getCategoryDAO().getObjects();
	}

	@Override
	public List<Category> getObjectsByIds(List<Integer> keys) {
		return getCategoryDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Category> getObjects(int limit) {
		return getCategoryDAO().getObjects(limit);
	}
}
