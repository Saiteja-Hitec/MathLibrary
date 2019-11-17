package com.math.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.math.entity.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {

}
