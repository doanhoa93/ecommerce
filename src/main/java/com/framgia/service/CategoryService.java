package com.framgia.service;

import java.util.List;

import com.framgia.bean.CategoryInfo;

public interface CategoryService extends BaseService<Integer, CategoryInfo> {
	CategoryInfo getParentCategory(Integer categoryId);

	boolean createCategory(CategoryInfo categoryInfo);

	boolean updateCategory(CategoryInfo oldCategoryInfo, CategoryInfo newCategoryInfo);

	List<CategoryInfo> getChildCategories(Integer categoryId);

	List<CategoryInfo> getCategoriesWithForNew(Integer categoryId);

	List<CategoryInfo> hotCategories(int limit);
}
