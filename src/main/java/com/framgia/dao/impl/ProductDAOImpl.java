package com.framgia.dao.impl;

import java.awt.Image;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.ProductDAO;
import com.framgia.model.CartProduct;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;

public class ProductDAOImpl extends BaseDAOImpl<Integer, Product> implements ProductDAO {

	public ProductDAOImpl() {
		super(Product.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Recent getRecent(Integer productId) {
		Criteria criteria = getSession().createCriteria(Recent.class);
		criteria.createAlias("products", "products", Criteria.LEFT_JOIN, Restrictions.eq("products.id", productId));
		return (Recent) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Promotion getPromotion(Integer productId) {
		Criteria criteria = getSession().createCriteria(Promotion.class);
		criteria.createAlias("products", "products", Criteria.LEFT_JOIN, Restrictions.eq("products.id", productId));
		return (Promotion) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Category getCategory(Integer productId) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.createAlias("products", "products", Criteria.LEFT_JOIN, Restrictions.eq("products.id", productId));
		return (Category) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> getOrderProducts(Integer productId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(OrderProduct.class);
		criteria.add(Restrictions.eq("product_id", productId));
		return (List<OrderProduct>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> getCartProducts(Integer productId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(CartProduct.class);
		criteria.add(Restrictions.eq("product_id", productId));
		return (List<CartProduct>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(Integer productId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("product_id", productId));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> getImages(Integer productId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Image.class);
		criteria.add(Restrictions.eq("product_id", productId));
		return (List<Image>) criteria.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Product> getProducts(List<Integer> productIds) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.in("id", productIds));
		return (List<Product>) criteria.list();
	}
}
