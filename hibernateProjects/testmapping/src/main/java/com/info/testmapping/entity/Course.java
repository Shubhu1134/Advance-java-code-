package com.info.testmapping.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder.Case;

@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
private String courseName;
private Student student ;

@ManyToMany(mappedBy="studentList",cascade=CascadeType.PERSIST)
private List<Course> courseList;



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public List<Course> getCourseList() {
	return courseList;
}

public void setCourseList(List<Course> courseList) {
	this.courseList = courseList;
}


}
