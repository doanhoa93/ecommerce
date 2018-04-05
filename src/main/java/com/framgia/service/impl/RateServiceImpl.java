package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.RateInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Product;
import com.framgia.model.Rate;
import com.framgia.model.User;
import com.framgia.service.RateService;

public class RateServiceImpl extends BaseServiceImpl implements RateService {

	@Override
	public RateInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toRateInfo(getRateDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RateInfo findById(Serializable key) {
		try {
			return ModelToBean.toRateInfo(getRateDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(RateInfo entity) {
		try {
			getRateDAO().delete(toRate(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public RateInfo saveOrUpdate(RateInfo entity) {
		try {
			return ModelToBean.toRateInfo(getRateDAO().saveOrUpdate(toRate(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<RateInfo> getObjects() {
		try {
			return getRateDAO().getObjects().stream().map(ModelToBean::toRateInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<RateInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getRateDAO().getObjectsByIds(keys).stream().map(ModelToBean::toRateInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<RateInfo> getObjects(int off, int limit) {
		try {
			return getRateDAO().getObjects(off, limit).stream().map(ModelToBean::toRateInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<RateInfo> getRates(Integer productId) {
		try {
			return getRateDAO().getRates(productId).stream().map(ModelToBean::toRateInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Rate toRate(RateInfo rateInfo) {
		Rate rate = getRateDAO().getFromSession(rateInfo.getId());
		if (rate == null) {
			rate = new Rate();
			rate.setId(rateInfo.getId());
			rate.setUser(new User(rateInfo.getUserId()));
			rate.setProduct(new Product(rateInfo.getProductId()));
		}

		rate.setRating(rateInfo.getRating());
		return rate;
	}
}
