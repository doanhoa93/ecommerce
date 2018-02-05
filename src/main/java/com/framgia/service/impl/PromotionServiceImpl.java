package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.service.PromotionService;

public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

	@Override
	public Product getProduct(Integer promotionId) {
		try {
			return getPromotionDAO().getProduct(promotionId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Promotion findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getPromotionDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Promotion findById(Serializable key) {
		try {
			return getPromotionDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Promotion entity) {
		try {
			getPromotionDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Promotion entity) {
		try {
			getPromotionDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Promotion> getObjects() {
		try {
			return getPromotionDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Promotion> getObjectsByIds(List<Integer> keys) {
		try {
			return getPromotionDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Promotion> getObjects(int limit) {
		try {
			return getPromotionDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}
}
