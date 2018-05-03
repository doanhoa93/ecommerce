package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;

import com.framgia.bean.PromotionInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.service.PromotionService;

public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

	@Override
	public PromotionInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public PromotionInfo findById(Serializable key, boolean lock) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().findById(key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public PromotionInfo saveOrUpdate(PromotionInfo entity) {
		try {
			return ModelToBean.toPromotionInfo(getPromotionDAO().saveOrUpdate(toPromotion(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(PromotionInfo entity) {
		try {
			Promotion promotion = getPromotionDAO().findById(entity.getId(), true);
			List<Product> products = getProductDAO().getProductsByPromotion(entity.getId()).stream()
			    .filter(object -> (object != null)).collect(Collectors.toList());
			for (Product product : products) {
				product.setPromotion(null);
				getProductDAO().saveOrUpdate(product);
			}

			getPromotionDAO().delete(promotion);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<PromotionInfo> getObjects() {
		try {
			return getPromotionDAO().getObjects().stream().map(ModelToBean::toPromotionInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<PromotionInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getPromotionDAO().getObjectsByIds(keys).stream()
			    .map(ModelToBean::toPromotionInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<PromotionInfo> getPromotions(String page, int limit, Order order) {
		try {
			int off = 0;
			if (StringUtils.isNotEmpty(page))
				off = (Integer.parseInt(page) - 1) * limit;

			return getPromotionDAO().getPromotions(off, limit, order).stream()
			    .map(ModelToBean::toPromotionInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
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
		promotion.setSaleOf(promotionInfo.getSaleOf());
		return promotion;
	}
}
