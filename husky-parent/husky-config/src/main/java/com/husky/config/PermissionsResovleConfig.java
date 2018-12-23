package com.husky.config;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.support.json.JSONUtils;


@Component
public class PermissionsResovleConfig implements BeanFactoryPostProcessor {

//	@PostConstruct
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(Controller.class);
		System.out.println(JSONUtils.toJSONString(beansWithAnnotation));
	}


}
