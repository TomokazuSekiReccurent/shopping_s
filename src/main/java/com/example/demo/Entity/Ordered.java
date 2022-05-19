package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Orderedのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="ordered")
public class Ordered {
	
	@Id
	@Column(name="code")
	private Integer code;
	
	@Column(name="user_code")
	private Integer user_code;
	
	@Column(name="ordered_date")
	private Date ordered_date;
	
	@Column(name="total_price")
	private Integer total_price;
	
	@Column(name="delete_flag")
	private Integer delete_flag;
		
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getUser_code() {
		return user_code;
	}

	public void setUser_code(Integer user_code) {
		this.user_code = user_code;
	}

	public Date getOrdered_date() {
		return ordered_date;
	}

	public void setOrdered_date(Date ordered_date) {
		this.ordered_date = ordered_date;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}

}
