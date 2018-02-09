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
import com.framgia.dao.RateDAO;
import com.framgia.dao.RecentDAO;
import com.framgia.dao.SuggestDAO;
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

	RateDAO getRateDAO();

	RecentDAO getRecentDAO();

	SuggestDAO getSuggestDAO();

	UserDAO getUserDAO();

	public T findBy(String attribute, Serializable key, boolean lock);

	public T findById(Serializable key);

	public T saveOrUpdate(T entity);

	public boolean delete(T entity);

	public List<T> getObjects();

	public List<T> getObjectsByIds(List<Integer> keys);

	public List<T> getObjects(int off, int limit);
}
