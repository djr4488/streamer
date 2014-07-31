package com.djr.streamer.auth.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by IMac on 7/29/2014.
 */
@WebFilter(
		urlPatterns = {"/api/feed/*"}
)
public class AuthFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

	}

	@Override
	public void destroy() {

	}
}
