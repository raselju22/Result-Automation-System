package com.final_year.app.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.final_year.app.domain.Authorities;
import com.final_year.app.domain.Teacher;
import com.final_year.app.domain.Users;
import com.final_year.app.service.AuthoritiesService;
import com.final_year.app.service.TeacherService;
import com.final_year.app.service.UsersService;







/*
 * Controller to hable admin panel and dashboard
 */
@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	
	@RequestMapping("")
	public String adminPanel(){
		
		
		return "admin/adminPanel";
	}
	
	@RequestMapping(value="/addTeacher", method = RequestMethod.GET)
	public String adminTeacherRegisterGet(Model model){
		
		

		Teacher teacher=new Teacher();
		model.addAttribute("teacher", teacher);
		
		
		return "admin/addTeacher";
	}
	
	@RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
	public String adminTeacherRegisterPost(RedirectAttributes model, @ModelAttribute("team") Teacher teacher) {
		
		model.addAttribute("successMessage", "Registration Complete");

		teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
		teacherService.addTeacher(teacher);
		
		
		System.out.println(teacher);
		
		Users users=new Users();
		users.setTeacherId(teacher.getId());
		users.setEnabled(true);
		users.setPassword(teacher.getPassword());
		users.setUsername(teacher.getUsername());
		
		usersService.addUsers(users);
		
		
		Authorities authorities=new Authorities();
		authorities.setAuthorityType("ROLE_TEACHER");
		authorities.setUsername(teacher.getUsername());
		
		
		
		authoritiesService.addAuthorities(authorities);
		
		
		return "redirect:/admin/panel/addTeacher";
	}
	
}
