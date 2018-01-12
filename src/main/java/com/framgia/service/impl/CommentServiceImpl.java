package com.framgia.service.impl;

import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

  @Override
  public User getUser(Integer commentId) {
    return getCommentDAO().getUser(commentId);
  }

  @Override
  public Product getProduct(Integer commentId) {
    return getCommentDAO().getProduct(commentId);
  }
}
