package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	
	 public CourseService(CourseRepository courseRepository) {
	      this.courseRepository = courseRepository;
	  }
     
	 
	 public Course create(Course course) {
       return courseRepository.save(course);
   }
	 
	 
	 public List<Course> getAll(String level) {
       if (level != null) {
           return courseRepository.findByLevel(level);
       }
       return courseRepository.findAll();
  }
	
      public Course getById(int id) {
           return courseRepository.findById(id) 
        		   .orElseThrow(() -> new RuntimeException("Course not found"));
   }
	  
  
    public Course updateStatus(int id, boolean status) {
        Course course = getById(id);
        course.setActive(status);
        return courseRepository.save(course);
    }
      
 
  public void delete(int id) {
      courseRepository.deleteById(id);
  }
  
  
     }
 



































