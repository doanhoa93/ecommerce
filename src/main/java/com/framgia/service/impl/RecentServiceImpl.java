package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Product;
import com.framgia.model.Recent;
import com.framgia.service.RecentService;

public class RecentServiceImpl extends BaseServiceImpl implements RecentService {

	@Override
	public Product getProduct(Integer recentId) {
		return getRecentDAO().getProduct(recentId);
	}

	@Override
	public Recent findBy(String attribute, Serializable key, boolean lock) {
		return getRecentDAO().findBy(attribute, key, lock);
	}

	@Override
	public Recent findById(Serializable key) {
		return getRecentDAO().findById(key);
	}

	@Override
	public boolean delete(Recent entity) {
		try {
			getRecentDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Recent entity) {
		try {
			getRecentDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Recent> getObjects() {
		return getRecentDAO().getObjects();
	}

	@Override
	public List<Recent> getObjectsByIds(List<Integer> keys) {
		return getRecentDAO().getObjectsByIds(keys);
	}

	@Override
	public List<Recent> getObjects(int limit) {
		return getRecentDAO().getObjects(limit);
	}
}
