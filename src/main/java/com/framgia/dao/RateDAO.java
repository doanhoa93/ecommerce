package com.framgia.dao;

import java.util.List;

import com.framgia.model.Rate;

public interface RateDAO extends BaseDAO<Integer, Rate> {
	List<Rate> getRates(Integer productId);

	Rate getRate(Integer userId, Integer productId);
}
