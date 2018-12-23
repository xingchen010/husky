package com.husky.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerOne {
	
	
	@RequestMapping(method=RequestMethod.GET,path="/query")
	public String query(String name) {
		return "aa";
	}
}
