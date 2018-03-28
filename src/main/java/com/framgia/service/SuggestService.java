package com.framgia.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.bean.SuggestInfo;

public interface SuggestService extends BaseService<Integer, SuggestInfo> {
	List<SuggestInfo> getSuggests(Integer userId, int off, int limit, Order order);

	boolean createSuggest(SuggestInfo suggestInfo);

	boolean updateSuggest(SuggestInfo oldSuggest, SuggestInfo newSuggest);
}
