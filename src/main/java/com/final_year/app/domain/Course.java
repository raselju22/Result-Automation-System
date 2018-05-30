package com.final_year.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Course {

	
	
	
	@Id
	
	private Integer courseId;
	private String courseName;
	@OneToOne
	private Teacher courseTeacher;
	
	private BigDecimal courseCredit;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="course")
	
	private List<Student> students=new ArrayList<Student>();
	
	public Course() {
		
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Teacher getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(Teacher courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public BigDecimal getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(BigDecimal courseCredit) {
		this.courseCredit = courseCredit;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	

	
	
	
}
