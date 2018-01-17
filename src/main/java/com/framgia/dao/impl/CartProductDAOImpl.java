package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CartProductDAO;
import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;

public class CartProductDAOImpl extends BaseDAOImpl<Integer, CartProduct> implements CartProductDAO {

	public CartProductDAOImpl() {
		super(CartProduct.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Cart getCart(Integer cartProductId) {
		Criteria criteria = getSession().createCriteria(Cart.class);
		criteria.createAlias("cart_products", "cart_products", Criteria.LEFT_JOIN,
		        Restrictions.eq("cart_products.id", cartProductId));
		return (Cart) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Integer cartProductId) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("cart_products", "cart_products", Criteria.LEFT_JOIN,
		        Restrictions.eq("cart_products.id", cartProductId));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CartProduct> getCartProducts(List<Integer> cartIds) {
		Criteria criteria = getSession().createCriteria(CartProduct.class);
		criteria.add(Restrictions.in("cart_id", cartIds));
		return (List<CartProduct>) criteria.list();
	}
}
