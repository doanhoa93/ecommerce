package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.ProductDAO;
import com.framgia.helper.ProductFilter;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;

public class ProductDAOImpl extends BaseDAOAbstract<Integer, Product> implements ProductDAO {

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
		Criteria criteria = getSession().createCriteria(OrderProduct.class);
		criteria.add(Restrictions.eq("product.id", productId));
		return (List<OrderProduct>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCarts(Integer productId) {
		Criteria criteria = getSession().createCriteria(Cart.class);
		criteria.add(Restrictions.eq("product.id", productId));
		return (List<Cart>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComments(Integer productId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("product.id", productId));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> getImages(Integer productId) {
		Criteria criteria = getSession().createCriteria(Image.class);
		criteria.add(Restrictions.eq("product.id", productId));
		return (List<Image>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> filterProducts(Integer categoryId, ProductFilter productFilter, int off, int limit) {
		Criteria criteria = getSession().createCriteria(Product.class);
		if (categoryId != null)
			criteria.add(Restrictions.eq("category.id", categoryId));
		criteria.add(Restrictions.like("name", "%" + productFilter.getName() + "%"));
		criteria.add(Restrictions.between("price", productFilter.getPriceLow(), productFilter.getPriceHigh()));
		criteria.setFirstResult(off);
		if (limit != 0)
			criteria.setMaxResults(limit);
		return (List<Product>) criteria.list();
	}
}
