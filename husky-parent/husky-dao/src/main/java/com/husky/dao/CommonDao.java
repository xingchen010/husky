package com.husky.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CommonDao {
	
	@Resource(name="mysqlDao")
	public GenericityDao mysqlDao;

}
