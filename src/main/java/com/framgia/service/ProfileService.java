package com.framgia.service;

import com.framgia.bean.ProfileInfo;
import com.framgia.bean.UserInfo;

public interface ProfileService extends BaseService<Integer, ProfileInfo> {
	UserInfo getUser(Integer profileId);
}
