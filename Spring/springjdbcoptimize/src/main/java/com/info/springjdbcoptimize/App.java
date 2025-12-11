package com.info.springjdbcoptimize;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.springjdbcoptimize.entity.Student;
import com.info.springjdbcoptimize.service.StudentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
         StudentService service =  context.getBean(StudentService.class);
         Student s = new Student();
         s.setId(7);
         s.setName("Manan");
         s.setCourse("UI/UX");
         if(service.save(s))
      	   System.out.println("Record saved...");
         else
      	   System.out.println("Something wrong...");
      }
    }

