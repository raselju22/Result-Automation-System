package com.final_year.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.final_year.app.domain.Course;

@Repository
public interface CourseDao  extends CrudRepository<Course, Integer>{

}
