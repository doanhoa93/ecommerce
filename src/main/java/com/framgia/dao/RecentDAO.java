package com.framgia.dao;

import com.framgia.model.Product;

public interface RecentDAO {
  Product getProduct(Integer recentId);
}
