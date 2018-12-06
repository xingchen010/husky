package com.husky.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource(locations= {"classpath*:spring/application-context.xml","classpath*:spring/spring-mvc.xml"})
public class ApplicationContext {

}
