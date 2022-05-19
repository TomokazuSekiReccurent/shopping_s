package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Categoriessのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="categories")
public class Categories {
	
	@Id
	@Column(name="code")
	private Integer code;
		
	@Column(name="name")
	private String name;
	
	@Column(name="delete_flag")
	private Integer delete_flag;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}

}
