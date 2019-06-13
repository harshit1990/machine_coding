package com.example.invoice.model;

public class Inventory {
	private String productName;
	private double price;
	private int quantity;
	private boolean isMedicalProd;
	private boolean isSet;
	
	public Inventory(String productName, double price, int quantity, boolean isMedicalProd,boolean isSet) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.isMedicalProd = isMedicalProd;
		this.isSet = isSet;
	}
	
	
	public boolean isSet() {
		return isSet;
	}


	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}


	@Override
	public String toString() {
		return "Inventory [productName=" + productName + ", price=" + price + ", quantity=" + quantity +"]";
	}

	public Inventory() {

	}

	public boolean isMedicalProd() {
		return isMedicalProd;
	}

	public void setMedicalProd(boolean isMedicalProd) {
		this.isMedicalProd = isMedicalProd;
	}



	public Inventory(String productName, double price, int quantity) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
