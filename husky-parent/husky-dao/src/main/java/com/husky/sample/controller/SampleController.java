package com.husky.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.husky.dao.CommonDao;

@RestController
public class SampleController {

	@Autowired
	private CommonDao commonDao;
	
	@RequestMapping("/sample1")
	public String  sample1() {
		return "aa";
	}
}
