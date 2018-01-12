package com.framgia.service.impl;

import com.framgia.model.User;
import com.framgia.service.ProfileService;

public class ProfileServiceImpl extends BaseServiceImpl implements ProfileService {

  @Override
  public User getUser(Integer profileId) {
    return getProfileDAO().getUser(profileId);
  }
}
