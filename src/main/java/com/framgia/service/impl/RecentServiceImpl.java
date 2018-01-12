package com.framgia.service.impl;

import com.framgia.model.Product;
import com.framgia.service.RecentService;

public class RecentServiceImpl extends BaseServiceImpl implements RecentService {

  @Override
  public Product getProduct(Integer recentId) {
    return getRecentDAO().getProduct(recentId);
  }
}
