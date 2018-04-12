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
	public RateInfo findById(Serializable key, boolean lock) {
		try {
			return ModelToBean.toRateInfo(getRateDAO().findById(key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RateInfo saveOrUpdate(RateInfo entity) {
		try {
			Rate rate = toRate(entity);
			ModelToBean.toRateInfo(getRateDAO().saveOrUpdate(rate));
			float rating = (float) getRateDAO().getRates(rate.getProduct().getId()).stream()
			    .mapToDouble(Rate::getRating).average().orElse(Double.NaN);
			Product product = getProductDAO().findById(rate.getProduct().getId(), true);
			product.setRating(rating);
			getProductDAO().saveOrUpdate(product);

			return entity;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(RateInfo entity) {
		try {
			Rate rate = getRateDAO().findById(entity.getId(), true);
			getRateDAO().delete(rate);
			return true;
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
	public List<RateInfo> getRates(Integer productId) {
		try {
			return getRateDAO().getRates(productId).stream().map(ModelToBean::toRateInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RateInfo getRate(Integer userId, Integer productId) {
		try {
			return ModelToBean.toRateInfo(getRateDAO().getRate(userId, productId));
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
