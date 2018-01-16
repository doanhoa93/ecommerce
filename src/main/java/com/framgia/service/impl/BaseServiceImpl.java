package com.framgia.service.impl;

import com.framgia.dao.CartDAO;
import com.framgia.dao.CartProductDAO;
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
import com.framgia.service.BaseService;

public class BaseServiceImpl implements BaseService {
  protected CartDAO cartDAO;
  protected CartProductDAO cartProductDAO;
  protected CategoryDAO categoryDAO;
  protected CommentDAO commentDAO;
  protected ImageDAO imageDAO;
  protected OrderDAO orderDAO;
  protected OrderProductDAO orderProductDAO;
  protected ProductDAO productDAO;
  protected ProfileDAO profileDAO;
  protected PromotionDAO promotionDAO;
  protected RecentDAO recentDAO;
  protected UserDAO userDAO;

  public CartDAO getCartDAO() {
    return cartDAO;
  }

  public void setCartDAO(CartDAO cartDAO) {
    this.cartDAO = cartDAO;
  }

  public CartProductDAO getCartProductDAO() {
    return cartProductDAO;
  }

  public void setCartProductDAO(CartProductDAO cartProductDAO) {
    this.cartProductDAO = cartProductDAO;
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

  public RecentDAO getRecentDAO() {
    return recentDAO;
  }

  public void setRecentDAO(RecentDAO recentDAO) {
    this.recentDAO = recentDAO;
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
}
