package com.husky.utils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext = null;
    
    Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
        logger.info("--------获取applicationContext----------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取所有的controller实例
     * @param clazz
     * @return
     */
    public static Map<String,Object> controllersBean(Class<? extends Annotation>  clazz){
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }
}
