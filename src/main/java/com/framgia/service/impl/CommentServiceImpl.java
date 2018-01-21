package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Comment;
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

	@Override
	public Comment findBy(String attribute, Serializable key) {
		return getCommentDAO().findBy(attribute, key);
	}

	@Override
	public Comment findById(Serializable key) {
		return getCommentDAO().findById(key);
	}

	@Override
	public void delete(Comment entity) {
		try {
			getCommentDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Comment entity) {
		try {
			getCommentDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Comment> getList() {
		return getCommentDAO().getList();
	}

	@Override
	public List<Comment> getList(List<Integer> keys) {
		return getCommentDAO().getList(keys);
	}

	@Override
	public List<Comment> getList(int limit) {
		return getCommentDAO().getList(limit);
	}

}
