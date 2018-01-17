package com.framgia.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.UserInfo;
import com.framgia.model.User;
import com.framgia.service.UserService;
import com.framgia.util.Encode;

@Controller
@RequestMapping(value = "/sessions")
public class SessionsController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(ModelMap model) {
		model.addAttribute("userInfo", new UserInfo());
		return "login";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") UserInfo userInfo, HttpServletRequest request,
	        HttpServletResponse response) {
		try {
			User user = checkCookie(request);
			if (user == null) {
				user = userService.findByEmail(userInfo.getEmail());
				if (user != null && user.getPassword().equals(Encode.encode(userInfo.getPassword()))) {
					if (userInfo.isRemember())
						response.addCookie(new Cookie("email", userInfo.getEmail()));

					request.getSession().putValue("userId", user.getId());
					return new ModelAndView("redirect:/");
				}
			} else {
				request.getSession().putValue("userId", user.getId());
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

	private User checkCookie(HttpServletRequest request) {
		User user = null;
		for (Cookie cookie : request.getCookies()) {
			user = userService.findByEmail(cookie.getValue());
			if (user != null)
				break;
		}
		return user;
	}
}
