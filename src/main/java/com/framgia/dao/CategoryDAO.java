package com.framgia.dao;

import java.util.List;

import com.framgia.model.Category;
import com.framgia.model.Product;

public interface CategoryDAO {
  Category getParentCategory(Integer categoryId);

  List<Product> getProducts(Integer categoryId);

  List<Category> getCatgories(List<Integer> categoryIds);
}
