package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Orderdetailのコンストラクタをexcelのデータ使って作成
@Entity
@Table(name="orderdetail")
public class Orderdetail {
	@Id
	@Column(name="code")
	private Integer code;
	
	@Column(name="ordered_code")
	private Integer ordered_code;
		
	@Column(name="items_code")
	private Integer items_code;
	
	@Column(name="delete_flag")
	private Integer delete_flag;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getOrdered_code() {
		return ordered_code;
	}

	public void setOrdered_code(Integer ordered_code) {
		this.ordered_code = ordered_code;
	}

	public Integer getItems_code() {
		return items_code;
	}

	public void setItems_code(Integer items_code) {
		this.items_code = items_code;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}


}
