package com.asianrapid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	@RequestMapping("/view/{pageName}")
	public String openPage(@PathVariable String pageName){
		System.out.println(pageName);
		return pageName;
	}
}
