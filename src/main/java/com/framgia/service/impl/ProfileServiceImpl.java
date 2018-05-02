package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;
import com.framgia.constant.Gender;
import com.framgia.helper.ModelToBean;
import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.ProfileService;

public class ProfileServiceImpl extends BaseServiceImpl implements ProfileService {

	@Override
	public ProfileInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toProfileInfo(getProfileDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProfileInfo findById(Serializable key, boolean lock) {
		try {
			return ModelToBean.toProfileInfo(getProfileDAO().findById(key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProfileInfo saveOrUpdate(ProfileInfo entity) {
		try {
			return ModelToBean.toProfileInfo(getProfileDAO().saveOrUpdate(toProfile(entity)));
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(ProfileInfo entity) {
		try {
			Profile profile = getProfileDAO().findById(entity.getId(), true);
			getProfileDAO().delete(profile);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<ProfileInfo> getObjects() {
		try {
			return getProfileDAO().getObjects().stream().map(ModelToBean::toProfileInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProfileInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getProfileDAO().getObjectsByIds(keys).stream().map(ModelToBean::toProfileInfo)
			    .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public UserInfo getUser(Integer profileId) {
		try {
			return ModelToBean.toUserInfo(getProfileDAO().getUser(profileId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Profile toProfile(ProfileInfo profileInfo) {
		Profile profile = getProfileDAO().getFromSession(profileInfo.getId());
		if (profile == null) {
			profile = new Profile();
			profile.setId(profileInfo.getId());
			profile.setUser(new User(profileInfo.getUserId()));
		}

		profile.setAddress(profileInfo.getAddress());
		profile.setBirthday(profileInfo.getBirthday());
		profile.setGender(Gender.getInt(profileInfo.getGender()));
		return profile;
	}
}
