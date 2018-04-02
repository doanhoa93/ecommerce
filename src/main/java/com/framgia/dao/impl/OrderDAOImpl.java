package com.framgia.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.constant.Status;
import com.framgia.dao.OrderDAO;
import com.framgia.helper.DateUtil;
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
		criteria.createAlias("orders", "orders", Criteria.LEFT_JOIN,
		    Restrictions.eq("orders.id", orderId));
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
	public List<Order> getOrders(Integer userId, int off, int limit,
	    org.hibernate.criterion.Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(off);

		if (userId != null)
			criteria.add(Restrictions.eq("user.id", userId));

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);
		return (List<Order>) criteria.list();
	}

	@Override
	public int getOrdersSizeWithStatus(int status) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", status));
		return criteria.list().size();
	}

	@Override
	public double[] getSalesByDate(Date startDate, Date endDate) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", Status.getIntStatus(Status.ACCEPT)));
		criteria.add(Restrictions.between("createdAt", startDate, endDate));
		ProjectionList projectionList = Projections.projectionList()
		    .add(Projections.sum("totalPrice"), "total");
		criteria.setProjection(projectionList);
		Object sale = criteria.uniqueResult();
		double results[] = new double[2];

		if (sale != null)
			results[0] = (double) sale;
		else
			results[0] = 0;

		DateUtil dateUtil = new DateUtil();
		criteria.add(Restrictions.between("createdAt", dateUtil.getPrevStartDate(startDate),
		    dateUtil.getPrevEndDate(endDate)));
		sale = criteria.uniqueResult();
		if (sale != null)
			results[1] = (double) sale;
		else
			results[1] = 0;
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersWithGuest(String sessionId, int off, int limit,
	    org.hibernate.criterion.Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(off);

		if (sessionId != null)
			criteria.add(Restrictions.eq("sessionId", sessionId));

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);
		return criteria.list();
	}
}
