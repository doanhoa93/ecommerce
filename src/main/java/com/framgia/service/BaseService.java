package com.framgia.service;

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

public interface BaseService {
  CartDAO getCartDAO();

  CartProductDAO getCartProductDAO();

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
}
