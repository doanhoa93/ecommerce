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
			logger.error(e);
			return null;
		}
	}

	@Override
	public SuggestInfo findById(Serializable key) {
		try {
			return ModelToBean.toSuggestInfo(getSuggestDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
			return null;
		}

		try {
			return ModelToBean.toSuggestInfo(getSuggestDAO().saveOrUpdate(toSuggest(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(SuggestInfo entity) {
		try {
			getSuggestDAO().delete(toSuggest(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<SuggestInfo> getObjects() {
		try {
			return getSuggestDAO().getObjects().stream().map(ModelToBean::toSuggestInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<SuggestInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getSuggestDAO().getObjectsByIds(keys).stream().map(ModelToBean::toSuggestInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<SuggestInfo> getSuggests(Integer userId, int off, int limit, Order order) {
		try {
			return getSuggestDAO().getSuggests(userId, off, limit, order).stream()
			    .map(ModelToBean::toSuggestInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean createSuggest(SuggestInfo suggestInfo) {
		try {
			Map<String, String> map = uploadFile.upload(suggestInfo.getAvatarFile());
			suggestInfo.setAvatar(map.get("url"));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

		try {
			Suggest suggest = new Suggest();
			suggest.setUser(getUserDAO().findById(suggestInfo.getUserId()));
			suggest.setStatus(Status.getIntStatus(Status.WAITING));
			suggest.setAvatar(suggestInfo.getAvatar());
			suggest.setCategory(suggestInfo.getCategory());
			suggest.setCreatedAt(new Date());
			suggest.setInformation(suggestInfo.getInformation());
			suggest.setName(suggestInfo.getName());
			suggest.setPrice(suggestInfo.getPrice());
			getSuggestDAO().saveOrUpdate(suggest);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSuggest(SuggestInfo oldSuggest, SuggestInfo newSuggest) {
		try {
			if (newSuggest.getAvatarFile() != null && !newSuggest.getAvatarFile().isEmpty()) {
				Map<String, String> map = uploadFile.upload(newSuggest.getAvatarFile());
				newSuggest.setAvatar(map.get("url"));
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

		try {
			Suggest suggest = getSuggestDAO().findById(oldSuggest.getId());
			suggest.setStatus(Status.getIntStatus(Status.WAITING));
			if (StringUtils.isNotEmpty(newSuggest.getAvatar()))
				suggest.setAvatar(newSuggest.getAvatar());
			suggest.setCategory(newSuggest.getCategory());
			suggest.setCreatedAt(new Date());
			suggest.setInformation(newSuggest.getInformation());
			suggest.setName(newSuggest.getName());
			suggest.setPrice(newSuggest.getPrice());
			getSuggestDAO().saveOrUpdate(suggest);

			return true;
		} catch (Exception e) {
			logger.error(e);
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
