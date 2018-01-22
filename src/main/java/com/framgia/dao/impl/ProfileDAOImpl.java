package com.framgia.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.ProfileDAO;
import com.framgia.model.Profile;
import com.framgia.model.User;

public class ProfileDAOImpl extends BaseDAOAbstract<Integer, Profile> implements ProfileDAO {

	public ProfileDAOImpl() {
		super(Profile.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(Integer profileId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("profiles", "profiles", Criteria.LEFT_JOIN, Restrictions.eq("profiles.id", profileId));
		return (User) criteria.uniqueResult();
	}
}
