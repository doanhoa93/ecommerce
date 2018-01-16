package com.framgia.dao;

import com.framgia.model.User;

public interface ProfileDAO {
  User getUser(Integer profileId);
}
