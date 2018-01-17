package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "homePage";
	}
}
