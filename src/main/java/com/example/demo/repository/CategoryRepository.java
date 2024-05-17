package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RecipeEntity;

public interface CategoryRepository extends JpaRepository<RecipeEntity, Integer> {

}