package com.framgia.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;

@Controller
@RequestMapping(value = "/sessions")
public class SessionsController extends BaseController {
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(ModelMap model) {
		model.addAttribute("userInfo", new UserInfo());
		return "login";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") UserInfo userInfo) {
		try {
			if (userService.validate(userInfo)) {
				return new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/sessions/new");
	}

	@RequestMapping(value = "/delete")
	public String destroy(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
