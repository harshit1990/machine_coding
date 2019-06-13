package com.experiment.interfaces;

import com.experiment.model.Items;

public interface IStock {

	public void updateItem(Items items, int quantity) ;

	public void checkoutItem(int itemId, int quantity) ;

	public void generateReport();
	
}
