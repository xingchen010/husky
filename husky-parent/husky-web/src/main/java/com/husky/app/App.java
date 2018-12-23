package com.husky.app;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.husky.utils.SpringUtil;

@ComponentScan(basePackages = { "com.husky", "com.melo.focus" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, })
@MapperScan("com.melo.focus.mapper")
@ServletComponentScan
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		// 获取所有controller的权限码，放到缓存中
		Map<String, Object> controllersBean = SpringUtil.controllersBean(Controller.class);
		Map<String,String> permissionMap = Maps.newHashMap();
		for (Map.Entry<String, Object> entry : controllersBean.entrySet()) {
			Class<? extends Object> bean = entry.getValue().getClass();

			RequestMapping typeRequestMapping = bean.getAnnotation(RequestMapping.class);
			String[] basePath = getPath(typeRequestMapping);
			Method[] methods = entry.getValue().getClass().getMethods();
			for (Method method : methods) {
				RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
				RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
				String[]  permissions = requiresPermissions.value();
				if (requiresPermissions != null && methodRequestMapping != null &&  permissions.length > 0) {
					String[] methodPath = getPath(methodRequestMapping);
					String path = getPath(basePath, methodPath);
					for (String permission : permissions) {
						permissionMap.put(permission, path);
					}
				}
			}
		}
	}

	private static String getPath(String[] basePaths, String[] methodPaths) {
		StringBuilder sb = new StringBuilder();
		for (String basePath : basePaths) {
			for (String methodPath : methodPaths) {
				sb.append(basePath).append(methodPath).append(",");
			}
		}
		return sb.toString();
	}

	private static String[] getPath(RequestMapping requestMapping) {
		String[] controllerBasePath = null;
		if (requestMapping != null) {
			controllerBasePath = requestMapping.value();
			if (controllerBasePath.length == 0) {
				controllerBasePath = requestMapping.path();
			}
		}
		return controllerBasePath;
	}
}
