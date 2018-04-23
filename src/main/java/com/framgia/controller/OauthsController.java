package com.framgia.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.service.FacebookService;
import com.framgia.social.CustomUserSocial;

@Controller
@RequestMapping(value = "oauths")
public class OauthsController extends BaseController {

	@Autowired
	private FacebookService facebookService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String authorize(@RequestParam("access_token") String accessToken)
	    throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		boolean result = false;
		String extendedAccessToken = facebookService.getExtendedAccessToken(accessToken);
		if (StringUtils.isNotEmpty(extendedAccessToken)) {
			request.getSession().setAttribute("accessToken", extendedAccessToken);
			CustomUserSocial userSocial = facebookService.getUserProfile(extendedAccessToken);
			userSocial.setPassword(extendedAccessToken);
			if (userService.createUser(userSocial)) {
				hashMap.put("user", userSocial);
				result = true;
			}
		}

		hashMap.put("result", result);
		return toJson(hashMap);
	}
}
