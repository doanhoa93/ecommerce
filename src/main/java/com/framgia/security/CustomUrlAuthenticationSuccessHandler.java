package com.framgia.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.framgia.constant.Role;

public class CustomUrlAuthenticationSuccessHandler
    extends SavedRequestAwareAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response,
	    Authentication authentication) throws IOException {
		if (response.isCommitted())
			return;

		String targetUrl = redirectUrl(authentication);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String redirectUrl(Authentication authentication) {
		String url;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority authority : authorities)
			roles.add(authority.getAuthority());

		if (isAdmin(roles))
			url = "/admin";
		else if (isUser(roles))
			url = "/";
		else
			url = "/403";

		return url;
	}

	private boolean isAdmin(List<String> roles) {
		return roles.contains(Role.Admin);
	}

	private boolean isUser(List<String> roles) {
		return roles.contains(Role.User);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
