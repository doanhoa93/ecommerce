package com.framgia.dao;

import com.framgia.model.Product;
import com.framgia.model.Recent;

public interface RecentDAO extends BaseDAO<Integer, Recent> {
	Product getProduct(Integer recentId);
}
