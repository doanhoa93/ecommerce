package com.framgia.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.dao.CartDAO;
import com.framgia.dao.CategoryDAO;
import com.framgia.dao.CommentDAO;
import com.framgia.dao.ImageDAO;
import com.framgia.dao.NotificationDAO;
import com.framgia.dao.OrderDAO;
import com.framgia.dao.OrderProductDAO;
import com.framgia.dao.ProductDAO;
import com.framgia.dao.ProfileDAO;
import com.framgia.dao.PromotionDAO;
import com.framgia.dao.RateDAO;
import com.framgia.dao.RecentDAO;
import com.framgia.dao.SuggestDAO;
import com.framgia.dao.UserDAO;

public class BaseServiceImpl {
	protected CartDAO cartDAO;
	protected CategoryDAO categoryDAO;
	protected CommentDAO commentDAO;
	protected ImageDAO imageDAO;
	protected NotificationDAO notificationDAO;
	protected OrderDAO orderDAO;
	protected OrderProductDAO orderProductDAO;
	protected ProductDAO productDAO;
	protected ProfileDAO profileDAO;
	protected PromotionDAO promotionDAO;
	protected RateDAO rateDAO;
	protected RecentDAO recentDAO;
	protected SuggestDAO suggestDAO;
	protected UserDAO userDAO;

	@Autowired
	public HttpServletRequest request;

	@Autowired
	public HttpServletResponse response;

	public Logger logger = Logger.getLogger(this.getClass());

	public CartDAO getCartDAO() {
		return cartDAO;
	}

	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

	public NotificationDAO getNotificationDAO() {
		return notificationDAO;
	}

	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderProductDAO getOrderProductDAO() {
		return orderProductDAO;
	}

	public void setOrderProductDAO(OrderProductDAO orderProductDAO) {
		this.orderProductDAO = orderProductDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	public PromotionDAO getPromotionDAO() {
		return promotionDAO;
	}

	public void setPromotionDAO(PromotionDAO promotionDAO) {
		this.promotionDAO = promotionDAO;
	}

	public RateDAO getRateDAO() {
		return rateDAO;
	}

	public void setRateDAO(RateDAO rateDAO) {
		this.rateDAO = rateDAO;
	}

	public RecentDAO getRecentDAO() {
		return recentDAO;
	}

	public void setRecentDAO(RecentDAO recentDAO) {
		this.recentDAO = recentDAO;
	}

	public SuggestDAO getSuggestDAO() {
		return suggestDAO;
	}

	public void setSuggestDAO(SuggestDAO suggestDAO) {
		this.suggestDAO = suggestDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
