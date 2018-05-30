package com.final_year.app.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.final_year.app.domain.Course;
import com.final_year.app.domain.Student;
import com.final_year.app.domain.Teacher;
import com.final_year.app.service.CourseService;
import com.final_year.app.service.TeacherService;
import com.final_year.app.service.impl.ExelService;

/*
 * Controller To Handle Home page and About Page,logout request 
 * 
 */

@Controller
public class HomeController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;
	@RequestMapping("/")
	public String homePage(){
		
		//Error testing
		//if(1==1)
		//throw new RuntimeException();
		
		
		
//		Teacher teacher=new Teacher();
//		teacher.setEmailAddress("touhid@gmail.com");
//		teacher.setPhoneNumber("01676973492");
//		teacher.setUsername("touhidcse12");
//		teacher.setPassword("12345");
//		teacher.setName("Touhidur");
//		teacher.setId(45);
		
		
		
			
		
		return "home";
	}
	


	
	
	@RequestMapping("/about")
	public String aboutPage(){
		return "about";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		cancelCookie(request, response);
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	void cancelCookie(HttpServletRequest request, HttpServletResponse response)
	   {
	     String cookieName = "remember-me";
	     Cookie cookie = new Cookie(cookieName, null);
	     cookie.setMaxAge(0);
	     cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
	     response.addCookie(cookie);
	   }
	
	
	
}
