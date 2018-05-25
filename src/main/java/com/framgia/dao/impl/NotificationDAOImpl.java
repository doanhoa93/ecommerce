package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifications(Integer userId, int off, int limit,
	    org.hibernate.criterion.Order order) {
		Criteria criteria = getSession().createCriteria(Notification.class);
		if (userId != null)
			criteria.add(Restrictions.eq("user.id", userId));

		criteria.setFirstResult(off);

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder((org.hibernate.criterion.Order) order);

		return (List<Notification>) criteria.list();
	}

	@Override
	public void updateAll(Integer userId) {
		Query query = getSession()
		    .createQuery("update Notification n set n.watched = true where n.user.id = :userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
	}
}
