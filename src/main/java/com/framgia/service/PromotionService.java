package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.PromotionInfo;

public interface PromotionService extends BaseService<Integer, PromotionInfo> {
	List<PromotionInfo> getPromotions(String page, int limit, Order order);
}
