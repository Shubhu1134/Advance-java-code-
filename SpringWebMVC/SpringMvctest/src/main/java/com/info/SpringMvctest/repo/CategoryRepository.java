package com.info.SpringMvctest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.SpringMvctest.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
