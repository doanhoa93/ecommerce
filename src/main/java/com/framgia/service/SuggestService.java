package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.SuggestInfo;

public interface SuggestService extends BaseService<Integer, SuggestInfo> {
	List<SuggestInfo> getSuggests(Integer userId, String page, int limit, Order order);
}
