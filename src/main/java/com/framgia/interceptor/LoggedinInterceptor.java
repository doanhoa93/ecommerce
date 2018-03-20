package com.framgia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framgia.bean.UserInfo;
import com.framgia.constant.Role;
import com.framgia.service.UserService;
import com.framgia.util.Encode;

public class LoggedinInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		UserInfo currentUser = (UserInfo) request.getSession().getAttribute("currentUser");
		if (currentUser == null) {
			currentUser = userService.getFromCookie(request);
			if (currentUser != null) {
				currentUser.setToken(Encode.generateToken());
				UserInfo user = userService.findById(currentUser.getId());
				user.setToken(currentUser.getToken());
				userService.saveOrUpdate(user);
				userService.validate(null);
				request.getSession().setAttribute("currentUser", currentUser);
			}
		}

		String uri = request.getRequestURI();
		if (currentUser != null
		        && (uri.equals("/Ecommerce/sessions/new") || uri.equals("/Ecommerce/registrations/new"))) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		if (currentUser != null && currentUser.getRole().equals(Role.Admin) && !uri.contains("admin")
		        && !uri.contains("images") && !uri.equals("/Ecommerce/sessions")) {
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
