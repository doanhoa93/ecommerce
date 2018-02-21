package com.framgia.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.bean.SuggestInfo;
import com.framgia.constant.Status;
import com.framgia.helper.ModelToBean;
import com.framgia.helper.UploadFile;
import com.framgia.model.Suggest;
import com.framgia.model.User;
import com.framgia.service.SuggestService;

public class SuggestServiceImpl extends BaseServiceImpl implements SuggestService {

	@Autowired
	private UploadFile uploadFile;

	@Override
	public SuggestInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toSuggestInfo(getSuggestDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public SuggestInfo findById(Serializable key) {
		try {
			return ModelToBean.toSuggestInfo(getSuggestDAO().findById(key));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(SuggestInfo entity) {
		try {
			getSuggestDAO().delete(toSuggest(entity));
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SuggestInfo> getObjects() {
		try {
			return getSuggestDAO().getObjects().stream().map(ModelToBean::toSuggestInfo).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<SuggestInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getSuggestDAO().getObjectsByIds(keys).stream().map(ModelToBean::toSuggestInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<SuggestInfo> getObjects(int off, int limit) {
		try {
			return getSuggestDAO().getObjects(off, limit).stream().map(ModelToBean::toSuggestInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<SuggestInfo> getSuggests(Integer userId, String page, int limit, Order order) {
		try {
			int off;
			if (StringUtils.isEmpty(page))
				off = 0;
			else
				off = (Integer.parseInt(page) - 1) * limit;

			return getSuggestDAO().getSuggests(userId, off, limit, order).stream().map(ModelToBean::toSuggestInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SuggestInfo saveOrUpdate(SuggestInfo entity) {
		try {
			if (entity.getAvatarFile() != null) {
				Map<String, String> map = uploadFile.upload(entity.getAvatarFile());
				entity.setAvatar(map.get("url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			return ModelToBean.toSuggestInfo(getSuggestDAO().saveOrUpdate(toSuggest(entity)));
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Suggest toSuggest(SuggestInfo suggestInfo) {
		Suggest suggest = getSuggestDAO().getFromSession(suggestInfo.getId());
		if (suggest == null) {
			suggest = new Suggest();
			suggest.setId(suggestInfo.getId());
			suggest.setUser(new User(suggestInfo.getUserId()));
		}
		suggest.setAvatar(suggestInfo.getAvatar());
		suggest.setCategory(suggestInfo.getCategory());
		suggest.setName(suggestInfo.getName());
		suggest.setCreatedAt(new Date());
		suggest.setPrice(suggestInfo.getPrice());
		suggest.setInformation(suggestInfo.getInformation());
		suggest.setStatus(Status.getIntStatus(suggestInfo.getStatus()));
		return suggest;
	}
}
