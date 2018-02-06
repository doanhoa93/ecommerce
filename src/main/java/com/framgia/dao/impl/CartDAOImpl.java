package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CartDAO;
import com.framgia.model.Cart;
import com.framgia.model.Product;
import com.framgia.model.User;

public class CartDAOImpl extends BaseDAOAbstract<Integer, Cart> implements CartDAO {

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
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.in("id", cartId));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCarts(Integer userId, int off, int limit) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.setFirstResult(off);
		if (limit != 0)
			criteria.setMaxResults(limit);
		return (List<Cart>) criteria.list();
	}
}
