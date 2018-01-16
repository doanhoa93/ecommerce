package com.framgia.service;

import com.framgia.model.Product;

public interface PromotionService extends BaseService {
  Product getProduct(Integer promotionId);
}
