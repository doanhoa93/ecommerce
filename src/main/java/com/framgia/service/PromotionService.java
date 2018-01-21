package com.framgia.service;

import com.framgia.model.Product;
import com.framgia.model.Promotion;

public interface PromotionService extends BaseService<Integer, Promotion> {
  Product getProduct(Integer promotionId);
}
