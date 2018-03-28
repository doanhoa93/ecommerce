package com.framgia.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.constant.Status;
import com.framgia.helper.DateUtil;

@Controller("admin/page")
@RequestMapping(value = "admin")
public class PagesController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminHomePage");
		DateUtil dateUtil = new DateUtil();
		model.addObject("orders", orderService.getNewOrders(dateUtil.getPrevWeek(), 0));
		model.addObject("products", productService.getNewProducts(dateUtil.getPrevWeek(), 0));
		model.addObject("categories", categoryService.getNewCategories(dateUtil.getPrevWeek(), 0));
		model.addObject("users", userService.getNewUsers(dateUtil.getPrevWeek(), 0));
		return model;
	}

	@RequestMapping(value = "statistic", method = RequestMethod.GET)
	public @ResponseBody String getStatistic() throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();

		HashMap<String, Integer> ordersMap = new HashMap<>();
		for (String status : Status.statuses)
			ordersMap.put(status, orderService.getOrdersSizeWithStatus(status));

		ArrayList<double[]> sales = new ArrayList<>();
		DateUtil dateUtil = new DateUtil();
		dateUtil.setYear(Calendar.getInstance().get(Calendar.YEAR));
		for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
			dateUtil.setMonth(month);
			sales.add(orderService.getSalesByDate(dateUtil.getStartDate(), dateUtil.getEndDate()));
		}

		hashMap.put("ordersStatistic", ordersMap);
		hashMap.put("salesStatistic", sales);
		return toJson(hashMap);
	}

	@RequestMapping(value = "tokens", method = RequestMethod.GET)
	public @ResponseBody String getTokens() throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("tokens", userService.getTokens());
		return toJson(hashMap);
	}
}
