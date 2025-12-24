package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repositry.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user)  throws Exception{
		return userRepo.save(user);
	}
}
