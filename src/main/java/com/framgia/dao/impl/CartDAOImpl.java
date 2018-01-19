package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CartDAO;
import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;

public class CartDAOImpl extends BaseDAOImpl<Integer, Cart> implements CartDAO {

	public CartDAOImpl() {
		super(Cart.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(Integer cartId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("carts", "carts", Criteria.LEFT_JOIN, Restrictions.eq("carts.id", cartId));
		return (User) criteria.uniqueResult();
	}

	@Override
	public Product getProduct(Integer cartId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.in("id", cartId));
		return (Product) criteria.uniqueResult();
	}
}
