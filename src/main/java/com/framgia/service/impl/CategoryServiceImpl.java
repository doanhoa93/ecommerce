package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.CategoryInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Category;
import com.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	@Override
	public CategoryInfo getParentCategory(Integer categoryId) {
		try {
			return ModelToBean.toCategoryInfo(getCategoryDAO().getParentCategory(categoryId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getChildCategories(Integer categoryId) {
		try {
			CategoryInfo category = null;
			List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
			do {
				category = getParentCategory(categoryId);
				if (category != null) {
					categories.add(category);
					categoryId = category.getId();
				}
			} while (category != null);
			return categories;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CategoryInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toCategoryInfo(getCategoryDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CategoryInfo findById(Serializable key) {
		try {
			return ModelToBean.toCategoryInfo(getCategoryDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(CategoryInfo entity) {
		try {
			getCategoryDAO().delete(toCategory(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	public CategoryInfo saveOrUpdate(CategoryInfo entity) {
		try {
			Category category = getCategoryDAO().saveOrUpdate(toCategory(entity));
			return ModelToBean.toCategoryInfo(category);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<CategoryInfo> getObjects() {
		try {
			return getCategoryDAO().getObjects().stream().map(ModelToBean::toCategoryInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getObjects(int off, int limit) {
		try {
			return getCategoryDAO().getObjects(off, limit).stream().map(ModelToBean::toCategoryInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getCategoryDAO().getObjectsByIds(keys).stream().map(ModelToBean::toCategoryInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Category toCategory(CategoryInfo categoryInfo) {
		Category category = getCategoryDAO().getFromSession(categoryInfo.getId());
		if (category == null) {
			category = new Category();
			category.setId(categoryInfo.getId());
		}

		category.setName(categoryInfo.getName());
		category.setParentId(categoryInfo.getParentId());
		return category;
	}
}
