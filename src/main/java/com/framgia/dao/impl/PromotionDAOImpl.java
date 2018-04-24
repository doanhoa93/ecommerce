package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.framgia.dao.PromotionDAO;
import com.framgia.model.Promotion;

public class PromotionDAOImpl extends BaseDAOAbstract<Integer, Promotion> implements PromotionDAO {

	public PromotionDAOImpl() {
		super(Promotion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promotion> getPromotions(int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		if (off != 0)
			criteria.setFirstResult(off);

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);

		return criteria.list();
	}
}
