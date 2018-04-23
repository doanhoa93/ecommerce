package com.framgia.service;

import com.framgia.social.CustomUserSocial;

public interface FacebookService {
	String getExtendedAccessToken(String accessToken);

	CustomUserSocial getUserProfile(String accessToken);
}
