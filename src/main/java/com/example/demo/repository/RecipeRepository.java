package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

	List<RecipeEntity> findByRecipeNameContaining(String keyword);

	List<RecipeEntity> findByCategoryId(Integer categoryId);

	List<RecipeEntity> findAllByOrderByIdAsc();


}