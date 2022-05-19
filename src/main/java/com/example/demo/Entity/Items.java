package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//itemsのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="items")
public class Items {
	
	@Id
	@Column(name="code")
	private Integer code;
		
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="picture")
	private String picture;
	
	@Column(name="stock")
	private Integer stock;
	
	@Column(name="category_key")
	private Integer category_key;
	
	@Column(name="delivary_days")
	private Integer delivary_days;
	
	@Column(name="seller_user_code")
	private Integer seller_user_code;
	
	@Column(name="date")
	private Date date;
	
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCategory_key() {
		return category_key;
	}

	public void setCategory_key(Integer category_key) {
		this.category_key = category_key;
	}

	public Integer getDelivary_days() {
		return delivary_days;
	}

	public void setDelivary_days(Integer delivary_days) {
		this.delivary_days = delivary_days;
	}

	public Integer getSeller_user_code() {
		return seller_user_code;
	}

	public void setSeller_user_code(Integer seller_user_code) {
		this.seller_user_code = seller_user_code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}

	@Column(name="delete_flag")
	private Integer delete_flag;
	
	}

