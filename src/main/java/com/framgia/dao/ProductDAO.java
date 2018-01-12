package com.framgia.dao;

import java.awt.Image;
import java.util.List;

import com.framgia.model.CartProduct;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;

public interface ProductDAO {
  Recent getRecent(Integer productId);

  Promotion getPromotion(Integer productId);

  Category getCategory(Integer productId);

  List<OrderProduct> getOrderProducts(Integer productId);

  List<CartProduct> getCartProducts(Integer productId);

  List<Comment> getComments(Integer productId);

  List<Image> getImages(Integer productId);

  List<Product> getProducts(List<Integer> productIds);
}
