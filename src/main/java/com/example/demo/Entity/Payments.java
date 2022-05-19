package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Paymentsのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="payments")
public class Payments {
	@Id
	@Column(name="code")
	private Integer code;
		
	@Column(name="user_code")
	private Integer user_code;
	
	
	@Column(name="name")
	private String name;
		
	@Column(name="bank_blunch")
	private Integer bank_blunch;
	
	
	@Column(name="bank_num")
	private Integer bank_num;
		
	@Column(name="bank_type")
	private Integer bank_type;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBank_blunch() {
		return bank_blunch;
	}

	public void setBank_blunch(Integer bank_blunch) {
		this.bank_blunch = bank_blunch;
	}

	public Integer getBank_num() {
		return bank_num;
	}

	public void setBank_num(Integer bank_num) {
		this.bank_num = bank_num;
	}

	public Integer getBank_type() {
		return bank_type;
	}

	public void setBank_type(Integer bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
}
