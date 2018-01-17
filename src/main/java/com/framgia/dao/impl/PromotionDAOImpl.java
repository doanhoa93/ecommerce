package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.PromotionDAO;
import com.framgia.model.Product;
import com.framgia.model.Promotion;

public class PromotionDAOImpl extends BaseDAOImpl<Integer, Promotion> implements PromotionDAO {

	public PromotionDAOImpl() {
		super(Promotion.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Integer promotionId) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("promotion_id", promotionId));
		return (Product) criteria.uniqueResult();
	}
}
