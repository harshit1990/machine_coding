package com.example.invoice;

import java.util.HashMap;

import com.example.invoice.model.Inventory;

public class InvoiceImpl implements Invoice {
	private int roi = 20;

	@Override
	public void start() {
		HashMap<Integer, Inventory> map = new HashMap<Integer, Inventory>(); ;
			System.out.print("\n----------------------Start test Case1------------------------------\n");
			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					map.put(i, new Inventory("wine", 20.0, 1, false,false));
				} else if (i == 1) {
					map.put(i, new Inventory("headache pills", 4.0, 2, true,false));
				} else if (i == 2) {
					map.put(i, new Inventory("pens", 10.0, 1, false,false));
				}
			}
			calculateTax(map);
			map.clear();
			
			System.out.print("\n----------------------Start test Case2------------------------------\n");
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					map.put(i, new Inventory("book", 30, 1, false,false));
				} else if (i == 1) {
					map.put(i, new Inventory("choclate", 1, 1, false,false));
				} 
			}
			calculateTax(map);
			map.clear();
			
			System.out.print("\n----------------------Start test Case3------------------------------\n");
			for (int i = 0; i < 1; i++) {
				map.put(i, new Inventory("pen", 5, 1, false,true));
			}
			calculateTax(map);
			map.clear();
	}

	private void calculateTax(HashMap<Integer, Inventory> map) {
		int length = map.size();
		double result = 0;
		double totalTax = 0;
		boolean flag =false;
		if (length == 0)
			System.out.println("No Data for invoice.Please input data");
		for (int i = 0; i < length; i++) {
			double totalPrice = 0;
			double tax = 0;
			Inventory calculateTaxation = map.get(i);
			if(!calculateTaxation.isSet()) {
				if (!calculateTaxation.isMedicalProd()) {
					double price = calculateTaxation.getPrice();
					int quantity = calculateTaxation.getQuantity();
					double newPrice = price * quantity;
					tax = calculateProductTax(newPrice);
					totalTax += tax;
					totalPrice = price + tax;
					System.out.println(quantity + " " + calculateTaxation.getProductName() + ":" + totalPrice);
					result += totalPrice;
				} else {
					totalPrice = calculateTaxation.getPrice() + tax;
					System.out.println(calculateTaxation.getQuantity() + " " + calculateTaxation.getProductName() + ":" + totalPrice);
					result += totalPrice;
				}
			} else {
				flag=true;
				break;
			}
		}
		
		
		if(flag) {
			System.out.println("ERROR: Indiviual Unit of Product. No Taxation defined");
		}else {
			System.out.println("Sale tax:" + totalTax);
			System.out.println("Total of invoice with tax:" + result);
		}
	}

	private double calculateProductTax(double price) {
		double result = ((price * roi) / 100);
		return result;
	}

}
