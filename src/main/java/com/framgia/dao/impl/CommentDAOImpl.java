package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.CommentDAO;
import com.framgia.model.Comment;
import com.framgia.model.Product;
import com.framgia.model.User;

public class CommentDAOImpl extends BaseDAOAbstract<Integer, Comment> implements CommentDAO {

	public CommentDAOImpl() {
		super(Comment.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(Integer commentId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("comments", "comments", Criteria.LEFT_JOIN,
		    Restrictions.eq("comments.id", commentId));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Integer commentId) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.createAlias("comments", "comments", Criteria.LEFT_JOIN,
		    Restrictions.eq("comments.id", commentId));
		return (Product) criteria.uniqueResult();
	}
}
