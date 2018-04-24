package com.framgia.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Order;

import com.framgia.bean.CategoryInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Category;
import com.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	@Override
	public CategoryInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			CategoryInfo categoryInfo = ModelToBean
			    .toCategoryInfo(getCategoryDAO().findBy(attribute, key, lock));
			Category parent = getCategoryDAO().findById(categoryInfo.getParentId(), lock);
			if (parent != null)
				categoryInfo.setParentName(parent.getName());
			return categoryInfo;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CategoryInfo findById(Serializable key, boolean lock) {
		try {
			return findBy("id", key, lock);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
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
	public boolean delete(CategoryInfo entity) {
		try {
			Category category = getCategoryDAO().findById(entity.getId(), true);
			getCategoryDAO().delete(category);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<CategoryInfo> getObjects() {
		try {
			return getCategoryDAO().getObjects().stream().map(ModelToBean::toCategoryInfo)
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
	public List<CategoryInfo> getCategoriesWithForNew(Integer categoryId) {
		try {
			List<Integer> categoryIds = getObjects().stream().map(CategoryInfo::getId)
			    .collect(Collectors.toList());
			categoryIds.remove(categoryId);
			return getObjectsByIds(categoryIds);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> hotCategories(int limit) {
		try {
			return getCategoryDAO().hotCategories(limit).stream().map(ModelToBean::toCategoryInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getNewCategories(Date date, int limit) {
		try {
			return getCategoryDAO().getNewObjects(date, limit).stream()
			    .map(ModelToBean::toCategoryInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CategoryInfo> getCategories(int off, int limit, Order order) {
		try {
			return getCategoryDAO().getCategories(off, limit, order).stream()
			    .map(ModelToBean::toCategoryInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean createCategory(CategoryInfo categoryInfo) {
		try {
			Category category = new Category();
			category.setName(categoryInfo.getName());
			if (isNewParentId(categoryInfo.getParentId())) {
				Category parent = new Category();
				parent.setName(categoryInfo.getParentName());
				parent = getCategoryDAO().saveOrUpdate(parent);
				categoryInfo.setParentId(parent.getId());
			}
			category.setParentId(categoryInfo.getParentId());
			category.setCreatedAt(new Date());
			getCategoryDAO().saveOrUpdate(category);

			categoryInfo.setId(category.getId());
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean updateCategory(CategoryInfo oldCategoryInfo, CategoryInfo newCategoryInfo) {
		try {
			Category category = getCategoryDAO().findById(oldCategoryInfo.getId(), true);
			category.setName(newCategoryInfo.getName());
			if (isNewParentId(newCategoryInfo.getParentId())) {
				Category parent = new Category();
				parent.setName(newCategoryInfo.getParentName());
				parent = getCategoryDAO().saveOrUpdate(parent);
				newCategoryInfo.setParentId(parent.getId());
			} else if (isDeleteParentId(newCategoryInfo.getParentId())) {
				category.setParentId(null);
			} else
				category.setParentId(newCategoryInfo.getParentId());
			getCategoryDAO().saveOrUpdate(category);

			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
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

	private boolean isNewParentId(Integer parentId) {
		return parentId != null && parentId == 0;
	}

	private boolean isDeleteParentId(Integer parentId) {
		return parentId != null && parentId == -1;
	}
}
