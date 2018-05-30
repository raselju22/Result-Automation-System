package com.final_year.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.final_year.app.domain.Teacher;

@Repository
public interface TeacherDao extends CrudRepository<Teacher, Long> {

	public Teacher findByUsername(String userName);
}
