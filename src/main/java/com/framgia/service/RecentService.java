package com.framgia.service;

import com.framgia.model.Product;
import com.framgia.model.Recent;

public interface RecentService extends BaseService<Integer, Recent> {
  Product getProduct(Integer recentId);
}
