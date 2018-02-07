package com.framgia.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.model.Suggest;

public interface SuggestDAO extends BaseDAO<Integer, Suggest> {
	List<Suggest> getSuggests(Integer userId, int off, int limit, Order order);
}
