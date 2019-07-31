package com.data.center.MedicalOrderCenter.domain;

import lombok.Data;

@Data
public class Medication {

private String id;
	
	private String name;
	
	private String type;
	
	private double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Medication(String id, String name, String type, double price) {
		
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Medication() {
		
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
}