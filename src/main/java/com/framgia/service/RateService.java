package com.framgia.service;

import java.util.List;

import com.framgia.bean.RateInfo;

public interface RateService extends BaseService<Integer, RateInfo> {
	List<RateInfo> getRates(Integer productId);
}
