package com.framgia.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.model.Promotion;

public interface PromotionDAO extends BaseDAO<Integer, Promotion> {
	List<Promotion> getPromotions(int off, int limit, Order order);
}
