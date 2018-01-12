package com.framgia.dao;

import com.framgia.model.Product;
import com.framgia.model.User;

public interface CommentDAO {
  User getUser(Integer commentId);

  Product getProduct(Integer commentId);
}
