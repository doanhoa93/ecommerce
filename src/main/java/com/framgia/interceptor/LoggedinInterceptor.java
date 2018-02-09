package com.framgia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framgia.constant.Role;
import com.framgia.model.User;

public class LoggedinInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		String uri = request.getRequestURI();
		if (currentUser != null
		        && (uri.equals("/Ecommerce/sessions/new") || uri.equals("/Ecommerce/registrations/new"))) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		if (currentUser != null && currentUser.getRole().equals(Role.Admin) && !uri.contains("admin")
		        && !uri.equals("/Ecommerce/sessions/delete")) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return false;
		}

		if (currentUser != null && currentUser.getRole().equals(Role.User) && uri.contains("admin")) {
			response.sendRedirect(request.getContextPath());
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
}
