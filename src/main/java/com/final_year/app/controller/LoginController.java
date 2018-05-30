package com.final_year.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 * Controller To Handle Pre  Login Request Which are accessible by everyone
 */

@Controller
public class LoginController {

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginGet(){
	
		return "login";
	}
	
	
	
}
