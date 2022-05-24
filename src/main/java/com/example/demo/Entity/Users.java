package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Usersのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code")
	private Integer code;
		
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
		
	@Column(name="tel")
	private String tel;
	
	@Column(name="email")
	private String email;
		
	@Column(name="pass")
	private String pass;
	
	@Column(name="delete_flag")
	private Integer delete_flag;
	
	//コンストラクタ
	public Users(Integer code,String name,String address,String tel,
			String email,String pass,Integer delete_flag) {
		super();
		this.code=code;
		this.name=name;
		this.address=address;
		this.tel=tel;
		this.email=email;
		this.pass=pass;
		this.delete_flag=delete_flag;
	}

	public Users() {
		
	}

	public Users(String name, String address, String tel, String email, String pass) {
		super();
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.pass = pass;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
		
	
}
