package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.ProductInfo;
import com.framgia.bean.RecentInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Product;
import com.framgia.model.Recent;
import com.framgia.service.RecentService;

public class RecentServiceImpl extends BaseServiceImpl implements RecentService {

	@Override
	public RecentInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toRecentInfo(getRecentDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RecentInfo findById(Serializable key, boolean lock) {
		try {
			return ModelToBean.toRecentInfo(getRecentDAO().findById(key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RecentInfo saveOrUpdate(RecentInfo entity) {
		try {
			return ModelToBean.toRecentInfo(getRecentDAO().saveOrUpdate(toRecent(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(RecentInfo entity) {
		try {
			Recent recent = getRecentDAO().findById(entity.getId(), true);
			getRecentDAO().delete(recent);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<RecentInfo> getObjects() {
		try {
			return getRecentDAO().getObjects().stream().map(ModelToBean::toRecentInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<RecentInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getRecentDAO().getObjectsByIds(keys).stream().map(ModelToBean::toRecentInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo getProduct(Integer recentId) {
		try {
			return ModelToBean.toProductInfo(getRecentDAO().getProduct(recentId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Recent toRecent(RecentInfo recentInfo) {
		Recent recent = getRecentDAO().getFromSession(recentInfo.getId());
		if (recent == null) {
			recent = new Recent();
			recent.setId(recentInfo.getId());
			recent.setProduct(new Product(recentInfo.getProductId()));
		}

		recent.setCreatedAt(recentInfo.getCreatedAt());
		return recent;
	}
}
