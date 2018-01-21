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
	public Recent findBy(String attribute, Serializable key) {
		return getRecentDAO().findBy(attribute, key);
	}

	@Override
	public Recent findById(Serializable key) {
		return getRecentDAO().findById(key);
	}

	@Override
	public void delete(Recent entity) {
		try {
			getRecentDAO().delete(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveOrUpdate(Recent entity) {
		try {
			getRecentDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Recent> getList() {
		return getRecentDAO().getList();
	}

	@Override
	public List<Recent> getList(List<Integer> keys) {
		return getRecentDAO().getList(keys);
	}

	@Override
	public List<Recent> getList(int limit) {
		return getRecentDAO().getList(limit);
	}
}
