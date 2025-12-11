package com.info.springjdbcoptimize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.springjdbcoptimize.dao.StudentDAO;
import com.info.springjdbcoptimize.entity.Student;

@Service
public class StudentService {
   @Autowired	
   private StudentDAO studentDAO;
   
   @Transactional
   public boolean save(Student s) {
	   return studentDAO.save(s);
   }
}







