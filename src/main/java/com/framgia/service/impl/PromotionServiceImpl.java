package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.ProductInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Promotion;
import com.framgia.service.PromotionService;

public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

	@Override
	public ProductInfo getProduct(Integer promotionId) {
		try {
			return ModelToBean.toProductInfo(getPromotionDAO().getProduct(promotionId));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PromotionInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PromotionInfo findById(Serializable key) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().findById(key));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(PromotionInfo entity) {
		try {
			getPromotionDAO().delete(toPromotion(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public PromotionInfo saveOrUpdate(PromotionInfo entity) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().saveOrUpdate(toPromotion(entity)));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<PromotionInfo> getObjects() {
		try {
			return getPromotionDAO().getObjects().stream().map(ModelToBean::toPromotionInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<PromotionInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getPromotionDAO().getObjectsByIds(keys).stream().map(ModelToBean::toPromotionInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<PromotionInfo> getObjects(int off, int limit) {
		try {
			return getPromotionDAO().getObjects(off, limit).stream().map(ModelToBean::toPromotionInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Promotion toPromotion(PromotionInfo promotionInfo) {
		Promotion promotion = getPromotionDAO().getFromSession(promotionInfo.getId());
		if (promotion == null) {
			promotion = new Promotion();
			promotion.setId(promotionInfo.getId());
		}

		promotion.setEndDate(promotionInfo.getEndDate());
		promotion.setStartDate(promotionInfo.getStartDate());
		return promotion;
	}
}
