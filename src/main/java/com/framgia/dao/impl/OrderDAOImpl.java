package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.OrderDAO;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.User;

public class OrderDAOImpl extends BaseDAOAbstract<Integer, Order> implements OrderDAO {

	public OrderDAOImpl() {
		super(Order.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(Integer orderId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("orders", "orders", Criteria.LEFT_JOIN, Restrictions.eq("orders.id", orderId));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> getOrderProducts(Integer orderId) {
		Criteria criteria = getSession().createCriteria(OrderProduct.class);
		criteria.add(Restrictions.eq("order.id", orderId));
		return (List<OrderProduct>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders(Integer userId, int off, int limit, org.hibernate.criterion.Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.setFirstResult(off);
		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);
		return (List<Order>) criteria.list();
	}
}
