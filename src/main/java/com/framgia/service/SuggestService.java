package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.SuggestInfo;
import com.framgia.model.Suggest;

public interface SuggestService extends BaseService<Integer, Suggest> {
	List<Suggest> getSuggests(Integer userId, String page, int limit, Order order);

	boolean saveOrUpdate(Integer userId, SuggestInfo suggestInfo) throws Exception;
}
