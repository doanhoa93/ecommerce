package com.framgia.service;

import com.framgia.model.Product;

public interface RecentService extends BaseService {
  Product getProduct(Integer recentId);
}
