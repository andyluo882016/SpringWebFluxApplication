package com.data.center.ProductOrderCenter.domain;

import java.util.Date;

public class Invoice {

	private Product produt;
	
	private Customer customer;
	
	private Date date;

	public Product getProdut() {
		return produt;
	}

	public void setProdut(Product produt) {
		this.produt = produt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Invoice(Product produt, Customer customer, Date date) {
		super();
		this.produt = produt;
		this.customer = customer;
		this.date = date;
	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Invoice [produt=" + produt + ", customer=" + customer + ", date=" + date + "]";
	}
	
	
	
	
	
}
