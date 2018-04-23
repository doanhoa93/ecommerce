package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.constant.Role;
import com.framgia.dao.UserDAO;
import com.framgia.model.Comment;
import com.framgia.model.Profile;
import com.framgia.model.User;

public class UserDAOImpl extends BaseDAOAbstract<Integer, User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findByEmail(String email) {
		return findBy("email", email, true);
	}

	@Override
	public Profile getProfile(Integer userId) {
		Criteria criteria = getSession().createCriteria(Profile.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (Profile) criteria.uniqueResult();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Comment> getComments(Integer userId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String role) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("role", role));
		return (List<User>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("role", Role.USER));

		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTokens() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("role", Role.USER));
		criteria.add(Restrictions.neOrIsNotNull("token", ""));
		criteria.setProjection(Projections.property("token"));
		return criteria.list();
	}
}
