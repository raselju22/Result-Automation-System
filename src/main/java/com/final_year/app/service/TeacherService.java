package com.final_year.app.service;

import com.final_year.app.domain.Teacher;

public interface TeacherService {

	public void addTeacher(Teacher teacher);
	public Teacher searchTeacherByUserName(String userName);
	public Teacher searchTeacherById(Long id);
}
