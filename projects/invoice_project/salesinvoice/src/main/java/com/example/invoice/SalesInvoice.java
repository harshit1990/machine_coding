package com.example.invoice;

public class SalesInvoice {

	public static void main(String[] args) {
		Invoice invoiceGenerator = new InvoiceImpl();
		invoiceGenerator.start();
	}

}
