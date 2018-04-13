package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
		criteria.createAlias("carts", "carts", Criteria.LEFT_JOIN,
		    Restrictions.eq("carts.id", cartId));
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
	public List<Cart> getCarts(Integer userId, int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.setFirstResult(off);
		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCartsWithGuest(String sessionId, int off, int limit, Order order) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sessionId", sessionId));
		criteria.setFirstResult(off);
		if (limit != 0)
			criteria.setMaxResults(limit);

		if (order != null)
			criteria.addOrder(order);
		return criteria.list();
	}

	@Override
	public Cart getCart(Integer userId, Integer productId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("product.id", productId));
		return (Cart) criteria.uniqueResult();
	}

	@Override
	public Cart getCart(String sessionId, Integer productId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("sessionId", sessionId));
		criteria.add(Restrictions.eq("product.id", productId));
		return (Cart) criteria.uniqueResult();
	}
}
