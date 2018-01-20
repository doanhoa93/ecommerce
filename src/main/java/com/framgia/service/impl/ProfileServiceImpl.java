package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.ProfileService;

public class ProfileServiceImpl extends BaseServiceImpl implements ProfileService {

	@Override
	public User getUser(Integer profileId) {
		return getProfileDAO().getUser(profileId);
	}

	@Override
	public Profile findBy(String attribute, Serializable key) {
		return getProfileDAO().findBy(attribute, key);
	}

	@Override
	public Profile findById(Serializable key) {
		return getProfileDAO().findById(key);
	}

	@Override
	public void delete(Profile entity) {
		try {
			getProfileDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Profile entity) {
		try {
			getProfileDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Profile> getObjects() {
		return getProfileDAO().getObjects();
	}

	@Override
	public List<Profile> getObjectsByIds(List<Integer> keys) {
		return getProfileDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Profile> getObjects(int limit) {
		return getProfileDAO().getObjects(limit);
	}
}
