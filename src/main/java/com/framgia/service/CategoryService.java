package com.framgia.service;

import java.util.List;

import com.framgia.model.Category;
import com.framgia.model.Product;

public interface CategoryService extends BaseService<Integer, Category> {
  Category getParentCategory(Integer categoryId);

  List<Product> getProducts(Integer categoryId);

  List<Category> getChildCategories(Integer categoryId);
}
