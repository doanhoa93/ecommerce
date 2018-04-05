package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.SuggestDAO;
import com.framgia.model.Suggest;

public class SuggestDAOImpl extends BaseDAOAbstract<Integer, Suggest> implements SuggestDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Suggest> getSuggests(Integer userId, int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(off);

		if (userId != null)
			criteria.add(Restrictions.eq("user.id", userId));

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);

		return criteria.list();
	}
}
