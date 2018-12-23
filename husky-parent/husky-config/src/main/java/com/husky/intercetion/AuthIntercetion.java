package com.husky.intercetion;

import static com.husky.enums.Polocy.LOGIN;
import static com.husky.enums.Polocy.NO;
import static com.husky.enums.Polocy.REQUIRED;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.husky.annotations.AuthOperation;
import com.husky.annotations.AuthResource;
import com.husky.enums.Polocy;
import com.husky.model.HeadBean;


public class AuthIntercetion implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(AuthIntercetion.class);
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===========enter AuthIntercetion===========");
//		配置跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*"); //允许所有域名访问
		response.addHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,token,Authorization");
		response.addHeader("Access-Control-Max-Age", "600000");
		
//		if(handler instanceof HandlerMethod) {
//	        HandlerMethod h = (HandlerMethod)handler;
//	        //同时要有AuthResource和AuthOperation才需要权限校验
//	        AuthResource authResource = h.getBean().getClass().getAnnotation(AuthResource.class);
//	        AuthOperation authOperation = h.getMethodAnnotation(AuthOperation.class);
//	        if(authResource != null && authOperation != null) {
//	        	Polocy polocy = authOperation.polocy();
//	        	//需要登录权限权限
//	        	if(polocy == LOGIN) {
//	        		//校验token 
//	        		HeadBean headBean = getHeadersInfo(request);
////	        		redisTemplate.opsForValue().set(RedisConstant.LOGIN_TOKEN_KEY, value);
//	        	}
//	        	
//	        	//需要service方法权限
//	        	else if(polocy == REQUIRED) {
//	        		
//	        	}
//	        	//不需要权限校验
//	        	else if(polocy == NO)
//	        		return true;
//	        }
//	        
//	        //判断后执行操作...
//	    }
		return true;
	}

	/**
	 * 获取header属性
	 * @param request
	 * @return
	 */
	private HeadBean getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = Maps.newHashMap();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		HeadBean hb = new HeadBean();
		try {
			BeanUtils.populate(hb, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}
		return hb;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
