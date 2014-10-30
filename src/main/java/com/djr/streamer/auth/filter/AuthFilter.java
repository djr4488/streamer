package com.djr.streamer.auth.filter;

import com.djr.streamer.auth.service.AuthService;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by IMac on 7/29/2014.
 */
@WebFilter(
		urlPatterns = {"/api/feed/*"}
)
public class AuthFilter implements Filter {
	@Inject
	private Logger log;
	@Inject
	private AuthService authService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	private boolean validateToken(String token) {
		return authService.isActiveToken(token);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		String token = req.getHeader("auth");
		log.info("doFilter() - token:{}", token);
		if (token != null && validateToken(token)) {
			log.debug("doFilter() - validated");
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			log.debug("doFilter() - not validated");
			HttpServletResponse resp = (HttpServletResponse) servletResponse;
			String url = req.getRequestURL().toString();
			log.debug("doFilter() = url:{}", url);
			resp.setStatus(Response.Status.FORBIDDEN.getStatusCode());
			log.debug("doFilter() - resp:{}", resp);
		}
	}

	@Override
	public void destroy() {

	}
}
