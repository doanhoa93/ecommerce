package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;
import com.framgia.helper.BeanToModel;
import com.framgia.model.User;

@Controller
@RequestMapping(value = "/registrations", method = RequestMethod.GET)
public class RegistrationsController extends BaseController {
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		return new ModelAndView("signup", "userInfo", new UserInfo());
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("userInfo") UserInfo userInfo) {
		try {
			User user = BeanToModel.toUser(userInfo);
			userService.saveOrUpdate(user);
			return new ModelAndView("redirect:/");
		} catch (Exception e) {
			return new ModelAndView("signup", "message", "Signup false");
		}
	}
}
