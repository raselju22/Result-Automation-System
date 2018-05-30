package com.final_year.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_year.app.dao.TeacherDao;
import com.final_year.app.domain.Teacher;
import com.final_year.app.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao dao;
	
	@Override
	public void addTeacher(Teacher teacher) {
		dao.save(teacher);
		
	}

	@Override
	public Teacher searchTeacherByUserName(String userName) {
		
		return dao.findByUsername(userName);
	}

	@Override
	public Teacher searchTeacherById(Long id) {
		
		return dao.findOne(id);
	}

}
