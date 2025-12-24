package com.example.SpringSecureUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SprngSecureUser.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
