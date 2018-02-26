package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
}
