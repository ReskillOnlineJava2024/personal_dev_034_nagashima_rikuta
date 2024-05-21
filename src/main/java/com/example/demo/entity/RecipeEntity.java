package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes") // 対応するテーブル名
public class RecipeEntity {

	// フィールド
	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番
	private Integer id; 
	
	@Column(name = "category_id")
	private Integer categoryId;

	private String name;
	
	@Column(name = "recipe_name")
	private String recipeName;
	
	private String materials;
	private String contents;
	
	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String getMaterials() {
		return materials;
	}

	public String getContents() {
		return contents;
	}
	
	public RecipeEntity(Integer id,Integer categoryId,String name,String recipeName,String materials,String contents) {
		this.id=id;
		this.categoryId=categoryId;
		this.name=name;
		this.recipeName=recipeName;
		this.materials=materials;
		this.contents=contents;
	}
	
	public RecipeEntity(Integer id,Integer categoryId, String recipeName, String materials, String contents) {	
		this.id=id;
		this.categoryId=categoryId;
		this.recipeName=recipeName;
		this.materials=materials;
		this.contents=contents;
	}
	
	public RecipeEntity(Integer categoryId, String recipeName, String materials, String contents) {	
		this.categoryId=categoryId;
		this.recipeName=recipeName;
		this.materials=materials;
		this.contents=contents;
	}
	
	public RecipeEntity(Integer categoryId, String name,String recipeName, String materials, String contents) {	
		this.categoryId=categoryId;
		this.name=name;
		this.recipeName=recipeName;
		this.materials=materials;
		this.contents=contents;
	}
	

	public RecipeEntity() {		
	}


}