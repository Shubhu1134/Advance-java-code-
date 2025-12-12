package com.info.springDataJpa;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.springDataJpa.entity.User;
import com.info.springDataJpa.service.UserService;

/**
 * Hello world!
 *
 */ 

public class App 
{
    public static void main( String[] args )
    {
     try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class)){ 
     UserService service =  context.getBean(UserService.class);
     List<User> userList =  service.getUserList();
     for(User user : userList)
   	  System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail());
           User user = new User();
     user.setEmail("anuj@gmail.com");
     user.setPassword("12345");
     user.setName("");
     service.saveUser(user);
     System.out.println("Record saved...");
		
	} catch (Exception e) {
	 System.out.println(e);
	}
    
    }
}