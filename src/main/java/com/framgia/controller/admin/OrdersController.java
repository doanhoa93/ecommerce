package com.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Status;

@Controller("admin/order")
@RequestMapping(value = "/admin/orders")
public class OrdersController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminOrders");
		model.addObject("orders", orderService.getObjects());
		model.addObject("statuses", Status.statuses);
		return model;
	}
}
