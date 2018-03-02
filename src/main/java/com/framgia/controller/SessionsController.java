package com.framgia.controller;

import java.util.Locale;

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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("userInfo") UserInfo userInfo) {
		ModelAndView model = new ModelAndView();
		try {
			if (userService.validate(userInfo)) {
				if (isAdmin(currentUser()))
					model.setViewName("redirect:/admin");
				else
					model.setViewName("redirect:/");
			} else {
				model.setViewName("login");
				model.addObject("message", messageSource.getMessage("session.error", null, Locale.US));
			}
			return model;
		} catch (Exception e) {
			logger.error(e);
			model.setViewName("redirect:/sessions/new");
			return model;
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String destroy(HttpServletRequest request) {
		userService.unremember();
		return "redirect:/";
	}
}
