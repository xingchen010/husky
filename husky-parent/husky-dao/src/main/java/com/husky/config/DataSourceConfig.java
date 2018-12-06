package com.husky.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath*:spring/datasource-config.xml"})
public class DataSourceConfig {

}
