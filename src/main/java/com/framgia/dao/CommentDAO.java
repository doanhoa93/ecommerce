package com.framgia.dao;

import com.framgia.model.Comment;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CommentDAO extends BaseDAO<Integer, Comment> {
	User getUser(Integer commentId);

	Product getProduct(Integer commentId);
}
