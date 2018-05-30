package com.final_year.app.service;

import com.final_year.app.domain.Course;

public interface CourseService {

	public void addNewCourseResult(Course course);
	public Course searchByCourseId(int courseId);
	
}
