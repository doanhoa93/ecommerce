package com.framgia.service;

import java.util.List;

import com.framgia.bean.CategoryInfo;

public interface CategoryService extends BaseService<Integer, CategoryInfo> {
	CategoryInfo getParentCategory(Integer categoryId);

	List<CategoryInfo> getChildCategories(Integer categoryId);
}
