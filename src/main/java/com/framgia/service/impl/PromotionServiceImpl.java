package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.service.PromotionService;

public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

	@Override
	public Product getProduct(Integer promotionId) {
		return getPromotionDAO().getProduct(promotionId);
	}

	@Override
	public Promotion findBy(String attribute, Serializable key) {
		return getPromotionDAO().findBy(attribute, key);
	}

	@Override
	public Promotion findById(Serializable key) {
		return getPromotionDAO().findById(key);
	}

	@Override
	public void delete(Promotion entity) {
		try {
			getPromotionDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Promotion entity) {
		getPromotionDAO().saveOrUpdate(entity);
	}

	@Override
	public List<Promotion> getList() {
		return getPromotionDAO().getList();
	}

	@Override
	public List<Promotion> getList(List<Integer> keys) {
		return getPromotionDAO().getList(keys);
	}

	@Override
	public List<Promotion> getList(int limit) {
		return getPromotionDAO().getList(limit);
	}
}
