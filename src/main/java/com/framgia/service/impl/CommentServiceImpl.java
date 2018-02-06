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
		try {
			return getCommentDAO().getUser(commentId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getProduct(Integer commentId) {
		try {
			return getCommentDAO().getProduct(commentId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Comment findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getCommentDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Comment findById(Serializable key) {
		try {
			return getCommentDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Comment entity) {
		try {
			getCommentDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Comment entity) {
		try {
			getCommentDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Comment> getObjects() {
		try {
			return getCommentDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comment> getObjectsByIds(List<Integer> keys) {
		try {
			return getCommentDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comment> getObjects(int off, int limit) {
		try {
			return getCommentDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}
}
