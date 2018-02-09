package com.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("admin")
@RequestMapping(value = "admin")
public class PagesController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "adminHomePage";
	}
}
