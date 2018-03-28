package com.framgia.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.constant.Status;
import com.framgia.helper.DateUtil;

@Controller
@RequestMapping(value = "admin/reports")
public class ReportsController extends AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("ReportView");
		model.addObject("newRecords", getNewRecords());
		model.addObject("sales", getSales());
		model.addObject("orders", getOrders());
		return model;
	}

	private ArrayList<ArrayList<String>> getNewRecords() {
		ArrayList<ArrayList<String>> newRecords = new ArrayList<ArrayList<String>>();
		newRecords
		    .add(new ArrayList<String>(Arrays.asList("orders", "products", "categories", "users")));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
		ArrayList<String> values = new ArrayList<>();
		values.add(String.valueOf(orderService.getNewOrders(calendar.getTime(), 0).size()));
		values.add(String.valueOf(productService.getNewProducts(calendar.getTime(), 0).size()));
		values.add(String.valueOf(categoryService.getNewCategories(calendar.getTime(), 0).size()));
		values.add(String.valueOf(userService.getNewUsers(calendar.getTime(), 0).size()));
		newRecords.add(values);
		return newRecords;
	}

	private ArrayList<ArrayList<String>> getSales() {
		ArrayList<ArrayList<String>> sales = new ArrayList<ArrayList<String>>();
		ArrayList<String> keys = new ArrayList<>();
		ArrayList<String> values = new ArrayList<>();
		DateUtil dateUtil = new DateUtil();
		dateUtil.setYear(Calendar.getInstance().get(Calendar.YEAR));
		for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
			dateUtil.setMonth(month);
			keys.add("Month " + String.valueOf(month + 1));
			values.add(String.valueOf(
			    orderService.getSalesByDate(dateUtil.getStartDate(), dateUtil.getEndDate())[0]));
		}
		sales.add(keys);
		sales.add(values);
		return sales;
	}

	private ArrayList<ArrayList<String>> getOrders() {
		ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
		orders.add(new ArrayList<String>(Arrays.asList(Status.statuses)));
		ArrayList<String> values = new ArrayList<>();
		for (String status : Status.statuses)
			values.add(String.valueOf(orderService.getOrdersSizeWithStatus(status)));
		orders.add(values);
		return orders;
	}
}
