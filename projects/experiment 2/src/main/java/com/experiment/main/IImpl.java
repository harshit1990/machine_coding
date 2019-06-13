package com.experiment.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.experiment.interfaces.IInterface;
import com.experiment.model.Items;

public class IImpl implements IInterface {
	final static Logger logger = LoggerFactory.getLogger(IImpl.class);
	
	public void startApplication(StockManagerImpl manager) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			manager.updateItem(new Items(1,"Pen",34.44), 10);
			manager.updateItem(new Items(2,"Shoe",30.09), 10);
			manager.updateItem(new Items(1,"Pen",34.44), 3);
			manager.updateItem(new Items(2,"Shoe",30.09), 3);
		});
		
		Thread t2 = new Thread(() -> {
			manager.checkoutItem(2, 5);
			manager.checkoutItem(1, 5);
			manager.checkoutItem(2, 5);
		});
		
		Thread t3 = new Thread(() -> {
			manager.generateReport();
			manager.generateReport();
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
	}

}
