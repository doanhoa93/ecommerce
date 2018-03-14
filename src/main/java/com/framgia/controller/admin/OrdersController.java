package com.framgia.controller.admin;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framgia.bean.OrderInfo;
import com.framgia.constant.Paginate;
import com.framgia.constant.Status;
import com.framgia.validator.OrderValidator;

@Controller("admin/order")
@RequestMapping(value = "admin/orders")
public class OrdersController extends AdminController {

	@Autowired
	private OrderValidator orderValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "entries", required = false) String entries) {
		ModelAndView model = new ModelAndView("adminOrders");
		if (StringUtils.isNotEmpty(entries)) {
			if (entries.equals("all"))
				model.addObject("orders", orderService.getOrders(null, 0, 0, Order.desc("createdAt")));
			else
				model.addObject("orders",
				        orderService.getOrders(null, 0, Integer.parseInt(entries), Order.desc("createdAt")));
		} else
			model.addObject("orders",
			        orderService.getOrders(null, 0, Paginate.ADMIN_OBJECT_LIMIT, Order.desc("createdAt")));
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id) {
		OrderInfo order = orderService.findById(id);
		ModelAndView model = new ModelAndView("adminOrder");
		if (order != null) {
			model.addObject("order", order);
			model.addObject("orderProducts", order.getOrderProducts());
		} else {
			model.setViewName("admin404");
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public @ResponseBody String update(@PathVariable("id") Integer id, @RequestBody String data, BindingResult result)
	        throws JsonProcessingException {
		HashMap<String, Object> hashMap = new HashMap<>();
		try {
			hashMap = toHashMap(data);
			OrderInfo orderInfo = orderService.findById(id);
			orderValidator.validStatus((String) hashMap.get("status"), orderInfo, result);
			if (result.hasErrors()) {
				hashMap.put("msg", messageSource.getMessage("error", null, Locale.US));
			} else {
				orderInfo.setStatus((String) hashMap.get("status"));
				if (orderInfo.getStatus().equals(Status.ACCEPT)) {
					if (!orderService.acceptOrder(orderInfo))
						hashMap.put("warning", Status.REJECT);
				} else
					orderService.updateStatusOrder(orderInfo);
				hashMap.put("msg", messageSource.getMessage("success", null, Locale.US));
			}
			return toJson(hashMap);
		} catch (Exception e) {
			logger.error(e);
			return "admin404";
		}
	}
}
