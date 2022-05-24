package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//itemsのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name = "items")
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private Integer code;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Integer price;

	@Column(name = "picture")
	private String picture;

	@Transient
	private Integer stock;

	@Column(name = "category_key")
	private Integer categoryKey;

	@Column(name = "delivary_days")
	private Integer delivaryDays;

	@Column(name = "seller_user_code")
	private Integer sellerUserCode;

	@Column(name = "date")
	private Date date;

	@Column(name = "delete_flag")
	private Integer deleteFlag;

	public Items() {
		super();
	}

	public Items(Integer code, String name, Integer price, String picture, Integer stock, Integer categoryKey,
			Integer delivaryDays, Integer sellerUserCode) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.stock = stock;
		this.categoryKey = categoryKey;
		this.delivaryDays = delivaryDays;
		this.sellerUserCode = sellerUserCode;
		this.date = new Date();
		this.deleteFlag = 0;
	}

//	public Items(Integer code, String name, Integer price, String picture) {
//		super();
//		this.code = code;
//		this.name = name;
//		this.price = price;
//		
//	}

	public Items(String name, Integer price,Integer delivaryDays,String picture) {
		this.name=name;
		this.price=price;
		this.delivaryDays=delivaryDays;
		this.picture=picture;
	
	}

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

	public Integer getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(Integer categoryKey) {
		this.categoryKey = categoryKey;
	}

	public Integer getDelivaryDays() {
		return delivaryDays;
	}

	public void setDelivaryDays(Integer delivaryDays) {
		this.delivaryDays = delivaryDays;
	}

	public Integer getSellerUserCode() {
		return sellerUserCode;
	}

	public void setSellerUserCode(Integer sellerUserCode) {
		this.sellerUserCode = sellerUserCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
