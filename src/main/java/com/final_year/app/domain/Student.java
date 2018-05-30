package com.final_year.app.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int classRoll;
	private int examRoll;
	
	private BigDecimal attendence;
	private BigDecimal tutorial_1;
	private BigDecimal tutorial_2;
	private BigDecimal tutorial_3;
	private BigDecimal tutorial_4;
	private BigDecimal tutorial_5;
	private BigDecimal tutorial_6;
	
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private Course course;
	Student(){
		
	}
	public Student(int classRoll, int examRoll, BigDecimal attendence, BigDecimal tutorial_1, BigDecimal tutorial_2,
			BigDecimal tutorial_3, BigDecimal tutorial_4, BigDecimal tutorial_5, BigDecimal tutorial_6
			) {
		super();
		this.classRoll = classRoll;
		this.examRoll = examRoll;
		this.attendence = attendence;
		this.tutorial_1 = tutorial_1;
		this.tutorial_2 = tutorial_2;
		this.tutorial_3 = tutorial_3;
		this.tutorial_4 = tutorial_4;
		this.tutorial_5 = tutorial_5;
		this.tutorial_6 = tutorial_6;
		
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getClassRoll() {
		return classRoll;
	}
	public void setClassRoll(int classRoll) {
		this.classRoll = classRoll;
	}
	public int getExamRoll() {
		return examRoll;
	}
	public void setExamRoll(int examRoll) {
		this.examRoll = examRoll;
	}
	public BigDecimal getAttendence() {
		return attendence;
	}
	public void setAttendence(BigDecimal attendence) {
		this.attendence = attendence;
	}
	public BigDecimal getTutorial_1() {
		return tutorial_1;
	}
	public void setTutorial_1(BigDecimal tutorial_1) {
		this.tutorial_1 = tutorial_1;
	}
	public BigDecimal getTutorial_2() {
		return tutorial_2;
	}
	public void setTutorial_2(BigDecimal tutorial_2) {
		this.tutorial_2 = tutorial_2;
	}
	public BigDecimal getTutorial_3() {
		return tutorial_3;
	}
	public void setTutorial_3(BigDecimal tutorial_3) {
		this.tutorial_3 = tutorial_3;
	}
	public BigDecimal getTutorial_4() {
		return tutorial_4;
	}
	public void setTutorial_4(BigDecimal tutorial_4) {
		this.tutorial_4 = tutorial_4;
	}
	public BigDecimal getTutorial_5() {
		return tutorial_5;
	}
	public void setTutorial_5(BigDecimal tutorial_5) {
		this.tutorial_5 = tutorial_5;
	}
	public BigDecimal getTutorial_6() {
		return tutorial_6;
	}
	public void setTutorial_6(BigDecimal tutorial_6) {
		this.tutorial_6 = tutorial_6;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", classRoll=" + classRoll + ", examRoll=" + examRoll + ", attendence="
				+ attendence + ", tutorial_1=" + tutorial_1 + ", tutorial_2=" + tutorial_2 + ", tutorial_3="
				+ tutorial_3 + ", tutorial_4=" + tutorial_4 + ", tutorial_5=" + tutorial_5 + ", tutorial_6="
				+ tutorial_6 + ", total=" + total + ", course=" + course + "]";
	}

	

	private BigDecimal calculateBest(int bestCount){
		ArrayList<BigDecimal> all=new ArrayList<>();
		all.add(tutorial_1);
		all.add(tutorial_2);
		all.add(tutorial_3);
		all.add(tutorial_4);
		all.add(tutorial_5);
		all.add(tutorial_6);
		Collections.sort(all,(b1,b2)->{
			
			return -b1.compareTo(b2);
		});
		
		System.out.println("ALL:"+all);
		BigDecimal t=new BigDecimal(0);
		
		for(int i=0;i<bestCount;i++){
			t=t.add(all.get(i));
		}
		
		System.out.println(bestCount+"::TOT:"+t);
		
		t=t.divide(new BigDecimal(bestCount),2, RoundingMode.HALF_UP);
		System.out.println("DIV:"+t+","+t.add(attendence));
		return t.add(attendence);
		
	}
	
	public void setTotal(int bestCount){
		total=calculateBest(bestCount);
	}
	
	
}
