package com.framgia.dao;

import com.framgia.model.Product;
import com.framgia.model.Promotion;

public interface PromotionDAO extends BaseDAO<Integer, Promotion> {
	Product getProduct(Integer promotionId);
}
