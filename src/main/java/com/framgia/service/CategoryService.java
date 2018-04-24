package com.framgia.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.CategoryInfo;

public interface CategoryService extends BaseService<Integer, CategoryInfo> {
	CategoryInfo getParentCategory(Integer categoryId);

	List<CategoryInfo> getChildCategories(Integer categoryId);

	List<CategoryInfo> getCategoriesWithForNew(Integer categoryId);

	List<CategoryInfo> hotCategories(int limit);

	List<CategoryInfo> getNewCategories(Date date, int limit);

	List<CategoryInfo> getCategories(int off, int limit, Order order);

	boolean createCategory(CategoryInfo categoryInfo);

	boolean updateCategory(CategoryInfo oldCategoryInfo, CategoryInfo newCategoryInfo);
}
