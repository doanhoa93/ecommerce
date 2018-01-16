package com.framgia.service;

import com.framgia.model.Product;
import com.framgia.model.User;

public interface CommentService extends BaseService {
  User getUser(Integer commentId);

  Product getProduct(Integer commentId);
}
