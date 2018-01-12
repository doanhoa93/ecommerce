package com.framgia.service.impl;

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
  public List<Category> getCatgories(List<Integer> categoryIds) {
    return getCategoryDAO().getCatgories(categoryIds);
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
}
