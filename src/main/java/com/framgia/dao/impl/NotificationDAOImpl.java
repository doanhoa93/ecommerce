package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.NotificationDAO;
import com.framgia.model.Notification;
import com.framgia.model.Order;
import com.framgia.model.User;

public class NotificationDAOImpl extends BaseDAOAbstract<Integer, Notification>
    implements NotificationDAO {

	public NotificationDAOImpl() {
		super(Notification.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(Integer notificationId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("notifications", "notifications", Criteria.LEFT_JOIN,
		    Restrictions.eq("notifications.id", notificationId));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Order getOrder(Integer notificationId) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.createAlias("notifications", "notifications", Criteria.LEFT_JOIN,
		    Restrictions.eq("notifications.id", notificationId));
		return (Order) criteria.uniqueResult();
	}
}
