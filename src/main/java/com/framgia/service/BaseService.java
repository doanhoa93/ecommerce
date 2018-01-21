package com.framgia.service;

import java.io.Serializable;
import java.util.List;

import com.framgia.dao.CartDAO;
import com.framgia.dao.CategoryDAO;
import com.framgia.dao.CommentDAO;
import com.framgia.dao.ImageDAO;
import com.framgia.dao.OrderDAO;
import com.framgia.dao.OrderProductDAO;
import com.framgia.dao.ProductDAO;
import com.framgia.dao.ProfileDAO;
import com.framgia.dao.PromotionDAO;
import com.framgia.dao.RecentDAO;
import com.framgia.dao.UserDAO;

public interface BaseService<PK, T> {
	CartDAO getCartDAO();

	CategoryDAO getCategoryDAO();

	CommentDAO getCommentDAO();

	ImageDAO getImageDAO();

	OrderDAO getOrderDAO();

	OrderProductDAO getOrderProductDAO();

	ProductDAO getProductDAO();

	ProfileDAO getProfileDAO();

	PromotionDAO getPromotionDAO();

	RecentDAO getRecentDAO();

	UserDAO getUserDAO();

	public T findBy(String attribute, Serializable key);

	public T findById(Serializable key);

	public void delete(T entity);

	public void saveOrUpdate(T entity);

	public List<T> getList();

	public List<T> getList(List<Integer> keys);

	public List<T> getList(int limit);
}
