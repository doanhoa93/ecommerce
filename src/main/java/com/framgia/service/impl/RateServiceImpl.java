package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Rate;
import com.framgia.service.RateService;

public class RateServiceImpl extends BaseServiceImpl implements RateService {

	@Override
	public Rate findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getRateDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Rate findById(Serializable key) {
		try {
			return getRateDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Rate entity) {
		try {
			getRateDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Rate entity) {
		try {
			getRateDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Rate> getObjects() {
		try {
			return getRateDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Rate> getObjectsByIds(List<Integer> keys) {
		try {
			return getRateDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Rate> getObjects(int off, int limit) {
		try {
			return getRateDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Rate> getRates(Integer productId) {
		try {
			return getRateDAO().getRates(productId);
		} catch (Exception e) {
			return null;
		}
	}
}
