package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.RecentDAO;
import com.framgia.model.Product;
import com.framgia.model.Recent;

public class RecentDAOImpl extends BaseDAOImpl<Integer, Recent> implements RecentDAO {

  @SuppressWarnings("deprecation")
  @Override
  public Product getProduct(Integer recentId) {
    Criteria criteria = getSession().createCriteria(Product.class);
    criteria.createAlias("recents", "recents", Criteria.LEFT_JOIN,
      Restrictions.eq("recents.id", recentId));
    return (Product) criteria.uniqueResult();
  }
}
