package com.final_year.app.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping("/result")
public class ResultController {

	private Path path;
	private static String UPLOADED_FOLDER = "F://temp//";

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/addResult", method = RequestMethod.GET)
	public String addReseult() {

		return "admin/addResult";
	}

	@RequestMapping(value = "/addResult", method = RequestMethod.POST)
	public String addReseultPost(RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam("bCount") Integer bestCount, @RequestParam("cName") String courseName,
			@RequestParam("cCredit") BigDecimal courseCredit, @RequestParam("cCode") Integer courseId,
			@RequestParam("file") MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload");
			return "redirect:/result/addResult";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

			String fileName = UPLOADED_FOLDER + "/" + file.getOriginalFilename();
			saveResultIntoDatabase(fileName, courseName, courseId, courseCredit, bestCount);

			//System.out.println("RRRRRRR:" + courseName + "," + courseId + "," + courseCredit + "," + bestCount);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";

	}

	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchReseult(Model model, @RequestParam(value = "courseId", required = false) Integer courseId) {

		if (courseId == null || courseId == 0){
			
			
			return "admin/searchResult";
		}
			

		else {
			Course course = courseService.searchByCourseId(courseId);
			if (course == null) {

				// System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
				return "admin/searchResult";
			}
			List<Student> students = course.getStudents();
			model.addAttribute("students", students);

			System.out.println(students);
			return "admin/searchResult";
		}
	}

	void saveResultIntoDatabase(String fileName, String courseName, int courseId, BigDecimal courseCredit,
			int bestCount) {
		ArrayList<Student> students = (ArrayList<Student>) ExelService.readXLSXFile(fileName);
		// System.out.println(students);

		Teacher teacher = getCustomer();

		Course course = new Course();
		course.setCourseId(courseId);

		for (Student s : students) {
			s.setCourse(course);
			s.setTotal(bestCount);

		}

		course.setCourseName(courseName);
		course.setCourseTeacher(teacher);
		course.setCourseCredit(courseCredit);

		course.getStudents().addAll(students);

		for (Student s : students) {
			s.setCourse(course);
		}

		courseService.addNewCourseResult(course);
	}

	private Teacher getCustomer() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Teacher teacher=teacherService.searchTeacherByUserName(username);
		
		return teacher;

	}

}
