package com.framgia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framgia.bean.UserInfo;
import com.framgia.constant.Role;
import com.framgia.service.UserService;

public class LoggedinInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		UserInfo currentUser = currentUser();
		if (currentUser != null) {
			String token = RequestContextHolder.currentRequestAttributes().getSessionId();
			String currentToken = currentUser.getToken();
			if (StringUtils.isEmpty(currentToken) || !currentToken.equals(token))
				currentUser = userService.updateToken(currentUser, token);
			request.getSession().setAttribute("currentUser", currentUser);
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
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	        throws Exception {
	}

	private UserInfo currentUser() {
		try {
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userService.findByEmail(user.getUsername());
		} catch (Exception e) {
			return null;
		}

	}

	private boolean validAdminRequest(HttpServletRequest request) {
		return request.getRequestURI().contains("admin") || request.getRequestURI().contains("assets");
	}

	private boolean isAdmin(UserInfo userInfo) {
		return userInfo != null && userInfo.getRole().equals(Role.Admin);
	}
}
