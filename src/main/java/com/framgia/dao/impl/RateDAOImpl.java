package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.RateDAO;
import com.framgia.model.Rate;

public class RateDAOImpl extends BaseDAOAbstract<Integer, Rate> implements RateDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Rate> getRates(Integer productId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("product.id", productId));
		return (List<Rate>) criteria.list();
	}
}
