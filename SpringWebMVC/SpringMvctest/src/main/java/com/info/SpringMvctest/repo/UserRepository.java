package com.info.SpringMvctest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.SpringMvctest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
