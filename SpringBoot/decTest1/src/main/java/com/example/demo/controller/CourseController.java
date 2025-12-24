package com.example.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;

@Controller
@RequestMapping("/Course")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService service) {
		this.service = service;
	}

	@PostMapping
	public Course create(@RequestBody Course course) {
		return service.create(course);
	}



	@GetMapping("/{id}")
	public Course get(@PathVariable int id) {
		return service.getById(id);
	}

  

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
}
