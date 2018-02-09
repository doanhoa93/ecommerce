package com.framgia.service;

import java.util.List;

import com.framgia.model.Rate;

public interface RateService extends BaseService<Integer, Rate> {
	List<Rate> getRates(Integer productId);
}
