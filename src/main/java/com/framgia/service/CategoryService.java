package com.framgia.service;

import java.util.List;

import com.framgia.model.Category;

public interface CategoryService extends BaseService<Integer, Category> {
  Category getParentCategory(Integer categoryId);

  List<Category> getChildCategories(Integer categoryId);
}
