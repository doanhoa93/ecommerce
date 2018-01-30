package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.framgia.model.Category;
import com.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	@Override
	public Category getParentCategory(Integer categoryId) {
		try {
			return getCategoryDAO().getParentCategory(categoryId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getChildCategories(Integer categoryId) {
		try {
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
		} catch (Exception e) {
			return null;
		}
	}

	public Category getCategory(Integer id) {
		try {
			return getCategoryDAO().findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Category findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getCategoryDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Category findById(Serializable key) {
		try {
			return getCategoryDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
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
		try {
			return getCategoryDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getObjectsByIds(List<Integer> keys) {
		try {
			return getCategoryDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getObjects(int limit) {
		try {
			return getCategoryDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}
}
