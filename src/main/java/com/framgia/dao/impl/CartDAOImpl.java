package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CartDAO;
import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> getCartProducts(Integer cartId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(CartProduct.class);
		criteria.add(Restrictions.eq("cart_id", cartId));
		return (List<CartProduct>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCarts(List<Integer> cartIds) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Cart.class);
		criteria.add(Restrictions.in("id", cartIds));
		return (List<Cart>) criteria.list();
	}
}
