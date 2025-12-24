package com.example.demo.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
      this.studentRepository = studentRepository;
  }

  
  public Student create(Student student) {
      return studentRepository.save(student);
  }


  public List<Student> getAll(int page, int size) {
      return studentRepository.findAll(PageRequest.of(page, size)).getContent();
  }


  public Student getById(int id) {
      return studentRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Student not found"));
  }


  public Student update(int id, Student updated) {
      Student student = getById(id);
      student.setName(updated.getName());
      student.setEmail(updated.getEmail());
      student.setPhone(updated.getPhone());
      return studentRepository.save(student);
      
  }
  

  public Student deactivate(int id) {
      Student student = getById(id);
      student.setActive(false);
      return studentRepository.save(student);
  }


  public void delete(int id) {
      studentRepository.deleteById(id);
  }
}