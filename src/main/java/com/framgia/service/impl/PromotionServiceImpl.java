package com.framgia.service.impl;

import com.framgia.model.Product;
import com.framgia.service.PromotionService;

public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

  @Override
  public Product getProduct(Integer promotionId) {
    return getPromotionDAO().getProduct(promotionId);
  }
}
