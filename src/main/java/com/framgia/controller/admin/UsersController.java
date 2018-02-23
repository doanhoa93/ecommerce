package com.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Role;

@Controller("admin/user")
@RequestMapping("admin/users")
public class UsersController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminUsers");
		model.addObject("users", userService.getUsers(Role.User));
		return model;
	}
}
