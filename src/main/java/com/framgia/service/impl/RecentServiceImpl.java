package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import com.framgia.model.Product;
import com.framgia.model.Recent;
import com.framgia.service.RecentService;

public class RecentServiceImpl extends BaseServiceImpl implements RecentService {

	@Override
	public Product getProduct(Integer recentId) {
		try {
			return getRecentDAO().getProduct(recentId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override

	public Recent findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getRecentDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Recent findById(Serializable key) {
		try {
			return getRecentDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
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
		try {
			return getRecentDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Recent> getObjectsByIds(List<Integer> keys) {
		try {
			return getRecentDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Recent> getObjects(int limit) {
		try {
			return getRecentDAO().getObjects(limit);
		} catch (Exception e) {
			return null;
		}
	}
}
