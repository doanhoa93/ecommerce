package com.framgia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framgia.bean.UserInfo;
import com.framgia.constant.Role;
import com.framgia.helper.CustomSession;
import com.framgia.service.CartService;
import com.framgia.service.OrderService;
import com.framgia.service.UserService;

public class LoggedinInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	    Object handler) throws Exception {
		UserInfo currentUser = currentUser();
		if (currentUser != null) {
			String token = CustomSession.current();
			String currentToken = currentUser.getToken();
			if (StringUtils.isEmpty(currentToken) || !currentToken.equals(token))
				currentUser = userService.updateToken(currentUser, token);
			request.getSession().setAttribute("currentUser", currentUser);
		}

		if (currentUser == null) {
			request.getSession().setAttribute("cartSize",
			    cartService.getCartsWithGuest(CustomSession.current(), null, 0, null).size());
			request.getSession().setAttribute("orderSize",
			    orderService.getOrdersWithGuest(CustomSession.current(), 0, 0, null).size());
		}

		if (isAdmin(currentUser) && !validAdminRequest(request)) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
	    Object handler, Exception ex) throws Exception {
	}

	private UserInfo currentUser() {
		try {
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
			    .getPrincipal();
			return userService.findByEmail(user.getUsername());
		} catch (Exception e) {
			return null;
		}

	}

	private boolean validAdminRequest(HttpServletRequest request) {
		return request.getRequestURI().contains("admin")
		    || request.getRequestURI().contains("assets");
	}

	private boolean isAdmin(UserInfo userInfo) {
		return userInfo != null && userInfo.getRole().equals(Role.Admin);
	}
}
