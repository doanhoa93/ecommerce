package com.framgia.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;
import com.framgia.constant.Gender;
import com.framgia.validator.UserValidator;

@Controller
@RequestMapping(value = "users")
public class UsersController extends BaseController {

	@Autowired
	private UserValidator userValidator;

	@SuppressWarnings("serial")
	@RequestMapping(value = "{id}")
	public ModelAndView show(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("user");
		model.addObject("user", userService.findById(id, false));
		model.addObject("genderes", new HashMap<String, String>() {
			{
				put("male", Gender.MALE);
				put("female", Gender.FEMALE);
			}
		});

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable Integer id, @ModelAttribute("user") UserInfo userInfo,
	    BindingResult result) {
		ModelAndView model = new ModelAndView("redirect");
		if (currentUser().getId() == userInfo.getId()) {
			userValidator.validateUpdate(userInfo, result);
			if (!result.hasErrors() && userService.updateUser(userInfo)) {
				model.addObject("url", request.getContextPath() + "/users/" + id);
			} else {
				model.setViewName("inputError");
				model.addObject("errors", convertErrorsToHashMap(result.getFieldErrors()));
			}
		} else
			model.addObject("url", request.getContextPath() + "/users/" + id);
		return model;
	}
}
