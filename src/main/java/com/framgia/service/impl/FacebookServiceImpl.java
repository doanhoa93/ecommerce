package com.framgia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.service.FacebookService;
import com.framgia.social.CustomUserSocial;
import com.framgia.social.Facebook;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;

public class FacebookServiceImpl implements FacebookService {

	@Autowired
	private Facebook facebook;

	@SuppressWarnings("deprecation")
	@Override
	public String getExtendedAccessToken(String accessToken) {
		try {
			return new DefaultFacebookClient()
			    .obtainExtendedAccessToken(facebook.getAppId(), facebook.getSecretId(), accessToken)
			    .getAccessToken();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public CustomUserSocial getUserProfile(String accessToken) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			return facebookClient.fetchObject("me", CustomUserSocial.class,
			    Parameter.with("fields", "id, name, email, picture.type(large), gender"));
		} catch (Exception e) {
			return null;
		}
	}

}
