package com.husky.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "crossDomainFilter",urlPatterns = {"/*"})
public class CrossDomainFilter implements Filter{
	
	private Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		logger.info("==========enter CrossDomainFilter==========");
		try {
			HttpServletRequest hreq = (HttpServletRequest) req;
			HttpServletResponse hresp = (HttpServletResponse) resp;
			//跨域
			hresp.setHeader("Access-Control-Allow-Origin", "*");
			//跨域 Header
			hresp.setHeader("Access-Control-Allow-Methods", "*");
			hresp.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
			// 浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
			// 配置options的请求返回
			if (hreq.getMethod().equals("OPTIONS")) {
				hresp.setStatus(HttpServletResponse.SC_OK);
				// hresp.setContentLength(0);
				hresp.getWriter().write("OPTIONS returns OK");
	            return;
	        }
			chain.doFilter(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
