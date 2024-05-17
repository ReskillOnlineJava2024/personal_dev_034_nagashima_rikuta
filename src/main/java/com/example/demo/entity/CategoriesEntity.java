package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories") // 対応するテーブル名
public class CategoriesEntity {

	// フィールド
	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番
	private Integer id; 
	
	@Column(name = "category_name")
	private String categoryName;

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	
	public CategoriesEntity(String categoryName) {
		this.categoryName=categoryName;
	}
	
	public CategoriesEntity(Integer id,String categoryName) {
		this.id=id;
		this.categoryName=categoryName;
	}
	
	public CategoriesEntity() {		
	}
}