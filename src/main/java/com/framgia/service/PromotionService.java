package com.framgia.service;

import com.framgia.bean.ProductInfo;
import com.framgia.bean.PromotionInfo;

public interface PromotionService extends BaseService<Integer, PromotionInfo> {
	ProductInfo getProduct(Integer promotionId);
}
