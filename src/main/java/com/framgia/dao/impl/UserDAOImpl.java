package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.UserDAO;
import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.Profile;
import com.framgia.model.User;

public class UserDAOImpl extends BaseDAOImpl<Integer, User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findByEmail(String email) {
		return findBy("email", email);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Profile getProfile(Integer userId) {
		Criteria criteria = getSession().createCriteria(Profile.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (Profile) criteria.uniqueResult();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Order> getOrders(Integer userId) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (List<Order>) criteria.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Cart> getCarts(Integer userId) {
		Criteria criteria = getSession().createCriteria(Cart.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (List<Cart>) criteria.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Comment> getComments(Integer userId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("user.id", userId));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Cart getCart(Integer userId, Integer productId) {
		Criteria criteria = getSession().createCriteria(Cart.class);
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("product.id", productId));
		return (Cart) criteria.uniqueResult();
	}
}
