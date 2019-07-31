package com.data.center.ProductOrderCenter.domain;

import lombok.Data;

@Data
public class Customer {

	private Integer id;
	
	private String name;
	
	private String cardNumber;
	
	private String pcid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public Customer(Integer id, String name, String cardNumber, String pcid) {
		
		this.id = id;
		this.name = name;
		this.cardNumber = cardNumber;
		this.pcid = pcid;
	}

	public Customer() {
		
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", cardNumber=" + cardNumber + ", pcid=" + pcid + "]";
	}

}
