package com.info.springDataJpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.springDataJpa.entity.User;
import com.info.springDataJpa.repo.UserRepository;

@Service
public class UserService {
   @Autowired	
   UserRepository userRepo;
   
   @Transactional
   public User saveUser(User user) {
	  return userRepo.save(user);
   }
   public List<User> getUserList(){
	   return userRepo.findAll();
   }
}