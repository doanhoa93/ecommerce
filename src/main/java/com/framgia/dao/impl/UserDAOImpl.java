package com.framgia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.UserDAO;
import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.Profile;
import com.framgia.model.User;

public class UserDAOImpl extends BaseDAOImpl<Integer, User> implements UserDAO {

  @Override
  public User findByEmail(String email) {
    return findBy("email", email);
  }

  @SuppressWarnings("deprecation")
  @Override
  public Profile getProfile(Integer userId) {
    Criteria criteria = getSession().createCriteria(Profile.class);
    criteria.add(Restrictions.eq("user_id", userId));
    return (Profile) criteria.uniqueResult();
  }

  @SuppressWarnings({ "unchecked", "deprecation" })
  @Override
  public List<Order> getOrders(Integer userId) {
    Criteria criteria = getSession().createCriteria(Order.class);
    criteria.add(Restrictions.eq("user_id", userId));
    return (List<Order>) criteria.list();
  }

  @SuppressWarnings({ "unchecked", "deprecation" })
  @Override
  public List<Cart> getCarts(Integer userId) {
    Criteria criteria = getSession().createCriteria(Cart.class);
    criteria.add(Restrictions.eq("user_id", userId));
    return (List<Cart>) criteria.list();
  }

  @SuppressWarnings({ "unchecked", "deprecation" })
  @Override
  public List<Comment> getComments(Integer userId) {
    Criteria criteria = getSession().createCriteria(Comment.class);
    criteria.add(Restrictions.eq("user_id", userId));
    return (List<Comment>) criteria.list();
  }

  @SuppressWarnings({ "deprecation", "unchecked" })
  @Override
  public List<User> getUsers(List<Integer> userIds) {
    Criteria criteria = getSession().createCriteria(User.class);
    criteria.add(Restrictions.in("id", userIds));
    return (List<User>) criteria.list();
  }
}
