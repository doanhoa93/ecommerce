package com.framgia.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.framgia.helper.ProductFilter;
import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;

public interface ProductDAO extends BaseDAO<Integer, Product> {
	Recent getRecent(Integer productId);

	Promotion getPromotion(Integer productId);

	Category getCategory(Integer productId);

	List<OrderProduct> getOrderProducts(Integer productId);

	List<Cart> getCarts(Integer productId);

	List<Comment> getComments(Integer productId);

	List<Image> getImages(Integer productId);

	List<Product> getProducts(Integer categoryId, int off, int limit, Order order);

	List<Product> filterProducts(Integer categoryId, ProductFilter productFilter, int off,
	    int limit, Order order);

	List<Product> getProductsByPromotion(Integer promotionId);

	List<Product> hotProducts(int limit);

	List<Product> recentProducts(Date date, int limit);

	List<Product> randomProducts(int limit);
}
