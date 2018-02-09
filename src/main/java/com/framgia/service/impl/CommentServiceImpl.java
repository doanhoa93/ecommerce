package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.CommentInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Comment;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

	@Override
	public UserInfo getUser(Integer commentId) {
		try {
			return ModelToBean.toUserInfo(getCommentDAO().getUser(commentId));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProductInfo getProduct(Integer commentId) {
		try {
			return ModelToBean.toProductInfo(getCommentDAO().getProduct(commentId));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CommentInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toCommentInfo(getCommentDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CommentInfo findById(Serializable key) {
		try {
			return ModelToBean.toCommentInfo(getCommentDAO().findById(key));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(CommentInfo entity) {
		try {
			getCommentDAO().delete(toComment(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public CommentInfo saveOrUpdate(CommentInfo entity) {
		try {
			return ModelToBean.toCommentInfo(getCommentDAO().saveOrUpdate(toComment(entity)));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<CommentInfo> getObjects() {
		try {
			return getCommentDAO().getObjects().stream().map(ModelToBean::toCommentInfo).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CommentInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getCommentDAO().getObjectsByIds(keys).stream().map(ModelToBean::toCommentInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CommentInfo> getObjects(int off, int limit) {
		try {
			return getCommentDAO().getObjects(off, limit).stream().map(ModelToBean::toCommentInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Comment toComment(CommentInfo commentInfo) {
		Comment comment = getCommentDAO().getFromSession(commentInfo.getId());
		if (comment == null) {
			comment = new Comment();
			comment.setId(commentInfo.getId());
			comment.setUser(new User(commentInfo.getUserId()));
			comment.setProduct(new Product(commentInfo.getProductId()));
		}

		comment.setContent(commentInfo.getContent());
		return comment;
	}
}
