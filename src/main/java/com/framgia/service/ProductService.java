package com.framgia.service;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Image;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;
import com.framgia.model.User;

public interface ProductService extends BaseService<Integer, Product> {
	Recent getRecent(Integer productId);

	Promotion getPromotion(Integer productId);

	Category getCategory(Integer productId);

	List<OrderProduct> getOrderProducts(Integer productId);

	List<Comment> getComments(Integer productId);

	List<Image> getImages(Integer productId);

	List<Order> getOrders(Integer productId);

	List<Cart> getCarts(Integer productId);

	List<User> getOrderedUser(Integer productId);
	
	List<Product> getProducts(Integer categoryId);

	List<Product> filterProducts(Integer categoryId, String name, String priceLow, String priceHigh);
}
