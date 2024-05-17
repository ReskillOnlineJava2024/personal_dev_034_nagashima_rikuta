package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // 対応するテーブル名
public class UserEntity {

	// フィールド
	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番
	private Integer id; 
	
	@Column(name = "user_name")
	private String userName;
	
	private String password; 

	// ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}
	
	public UserEntity(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}
	
	public UserEntity(Integer id,String userName, String password) {
		this.id=id;
		this.userName=userName;
		this.password=password;
	}
	
	public UserEntity() {		
	}

}
