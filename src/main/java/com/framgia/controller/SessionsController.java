package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;

@Controller
public class SessionsController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getNew() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("user", new UserInfo());
		return model;
	}
}
