package com.framgia.service;

import com.framgia.model.Profile;
import com.framgia.model.User;

public interface ProfileService extends BaseService<Integer, Profile> {
  User getUser(Integer profileId);
}
