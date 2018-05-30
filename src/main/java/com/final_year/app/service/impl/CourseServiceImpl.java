package com.final_year.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_year.app.dao.CourseDao;
import com.final_year.app.domain.Course;
import com.final_year.app.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDao dao;
	@Override
	public void addNewCourseResult(Course course) {
		dao.save(course);
		
	}

	@Override
	public Course searchByCourseId(int courseId) {
		
		return dao.findOne(courseId);
	}

	
}
