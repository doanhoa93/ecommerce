package com.framgia.controller;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.NotificationInfo;
import com.framgia.constant.Paginate;
import com.framgia.service.NotificationService;

@Controller
@RequestMapping(value = "notifications")
public class NotificationsController extends BaseController {

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView("notifications");
		if (currentUser() != null) {
			List<NotificationInfo> notifications = notificationService.getNotifications(
			    currentUser().getId(), page, Paginate.NOTIFICATION_PAGE, Order.desc("createdAt"));
			model.addObject("notifications", notifications);
			model.addObject("paginate",
			    setPaginate(page,
			        notifications.size(), notificationService
			            .getNotifications(currentUser().getId(), null, 0, null).size(),
			        Paginate.NOTIFICATION_PAGE));
		} else {
			model.setViewName("404");
		}

		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public @ResponseBody String update(@PathVariable("id") Integer id) {
		if (currentUser() != null) {
			if (id == 0) {
				notificationService.updateAll(currentUser().getId());
			} else {
				notificationService.update(id);
			}
		}

		return null;
	}
}
