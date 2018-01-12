package com.framgia.service;

import com.framgia.model.User;

public interface ProfileService extends BaseService {
  User getUser(Integer profileId);
}
