package com.framgia.dao;

import com.framgia.model.Profile;
import com.framgia.model.User;

public interface ProfileDAO extends BaseDAO<Integer, Profile> {
	User getUser(Integer profileId);
}
