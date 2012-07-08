package com.webdevelopersdiary.jamp.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * A dummy filter that filters nothing.
 */
public class DummyFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws IOException, ServletException {
		// Bypass the filter chain and go to servlet immediately
		req.getRequestDispatcher(((HttpServletRequest) req).getServletPath())
		.include(req, res);
	}
	public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}
}
