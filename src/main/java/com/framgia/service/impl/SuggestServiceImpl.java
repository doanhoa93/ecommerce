package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.bean.SuggestInfo;
import com.framgia.helper.BeanToModel;
import com.framgia.helper.UploadFile;
import com.framgia.model.Suggest;
import com.framgia.service.SuggestService;

public class SuggestServiceImpl extends BaseServiceImpl implements SuggestService {
	@Autowired
	private UploadFile uploadFile;

	@Override
	public Suggest findBy(String attribute, Serializable key, boolean lock) {
		try {
			return getSuggestDAO().findBy(attribute, key, lock);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Suggest findById(Serializable key) {
		try {
			return getSuggestDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Suggest entity) {
		try {
			getSuggestDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean saveOrUpdate(Suggest entity) {
		try {
			getSuggestDAO().saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean saveOrUpdate(Integer userId, SuggestInfo suggestInfo) throws Exception {
		try {
			suggestInfo.setUserId(userId);
			Suggest suggest = BeanToModel.toSuggest(suggestInfo);
			Map<String, String> map;
			map = uploadFile.upload(suggestInfo.getAvatarFile());
			suggest.setAvatar(map.get("url"));
			return saveOrUpdate(suggest);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Suggest> getObjects() {
		try {
			return getSuggestDAO().getObjects();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Suggest> getObjectsByIds(List<Integer> keys) {
		try {
			return getSuggestDAO().getObjectsByIds(keys);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Suggest> getObjects(int off, int limit) {
		try {
			return getSuggestDAO().getObjects(off, limit);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Suggest> getSuggests(Integer userId, String page, int limit, Order order) {
		try {
			int off;
			if (StringUtils.isEmpty(page))
				off = 0;
			else
				off = (Integer.parseInt(page) - 1) * limit;

			return getSuggestDAO().getSuggests(userId, off, limit, order);
		} catch (Exception e) {
			return null;
		}
	}
}
