package com.husky.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener;
/**
 * 解决远程部署的内存泄露问题
 * @author swq
 * @create_date 2018年12月23日
 */
@Configuration
public class WebConfig {
	@Bean
	public  ServletListenerRegistrationBean<ClassLoaderLeakPreventorListener> classLoaderLeakPreventorListener() {
		ServletListenerRegistrationBean<ClassLoaderLeakPreventorListener> registrationBean = new ServletListenerRegistrationBean<>(new ClassLoaderLeakPreventorListener());
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
