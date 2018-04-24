package com.framgia.controller;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;
import com.framgia.constant.Gender;
import com.framgia.constant.Role;

@Controller
@RequestMapping(value = "registrations")
public class RegistrationsController extends BaseController {

	@SuppressWarnings("serial")
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		ModelAndView model = new ModelAndView("signup");
		model.addObject("genderes", new HashMap<String, String>() {
			{
				put("male", Gender.MALE);
				put("female", Gender.FEMALE);
			}
		});
		model.addObject("user", new UserInfo());
		
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") UserInfo userInfo) {
		try {
			userInfo.setRole(Role.USER);
			ModelAndView model = new ModelAndView("redirect:/login");
			if (!userService.createUser(userInfo)) {
				model.addObject("user", userInfo);
				model.setViewName("signup");
			}

			return model;
		} catch (Exception e) {
			logger.error(e);
			return new ModelAndView("signup", "message",
			    messageSource.getMessage("registration.error", null, Locale.US));
		}
	}
}
