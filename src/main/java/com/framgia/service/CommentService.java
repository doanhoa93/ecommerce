package com.framgia.service;

import com.framgia.model.Comment;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CommentService extends BaseService<Integer, Comment> {
  User getUser(Integer commentId);

  Product getProduct(Integer commentId);
}
