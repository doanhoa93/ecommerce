package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.ProfileService;

public class ProfileServiceImpl extends BaseServiceImpl implements ProfileService {

	@Override
	public User getUser(Integer profileId) {
		try {
			return getProfileDAO().getUser(profileId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Profile findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getProfileDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Profile findById(Serializable key) {
		try {
			return getProfileDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Profile entity) {
		try {
			getProfileDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Profile entity) {
		try {
			getProfileDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Profile> getObjects() {
		try {
			return getProfileDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Profile> getObjectsByIds(List<Integer> keys) {
		try {
			return getProfileDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Profile> getObjects(int off, int limit) {
		try {
			return getProfileDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}
}
