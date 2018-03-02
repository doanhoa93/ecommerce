package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.ChatDAO;
import com.framgia.model.Chat;
import com.framgia.model.User;

public class ChatDAOImpl extends BaseDAOAbstract<Integer, Chat> implements ChatDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> getChats(Integer userId, int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		if (off != 0)
			criteria.add(Restrictions.lt("id", off));

		if (userId != null) {
			Criterion criteria1 = Restrictions.eq("sender.id", userId);
			Criterion criteria2 = Restrictions.eq("receiver.id", userId);
			criteria.add(Restrictions.or(criteria1, criteria2));
		}

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);

		return criteria.list();
	}

	@Override
	public void updateReadAll(Integer receiverId) {
		Query query = getSession().createQuery("update Chat c set c.watched = true where c.receiver.id = :receiverId");
		query.setParameter("receiverId", receiverId);
		query.executeUpdate();
	}

	@Override
	public void updateReadAll(Integer senderId, Integer receiverId) {
		Query query = getSession()
		        .createQuery("UPDATE Chat c SET c.watched = true WHERE c.receiver.id = :receiverId AND "
		                + "c.sender.id = :senderId");
		query.setParameter("receiverId", receiverId);
		query.setParameter("senderId", senderId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getRecentUsers(Integer receiverId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("receiver.id", receiverId));
		criteria.addOrder(Order.desc("createdAt"));
		criteria.setProjection(Projections.distinct(Projections.property("sender.id")));
		List<Integer> userIds = criteria.list();
		return getUsersByIdsWithOrder(userIds);
	}
}
