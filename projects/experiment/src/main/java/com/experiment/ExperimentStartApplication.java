package com.experiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.experiment.interfaces.IInterface;
import com.experiment.main.IImpl;
import com.experiment.main.StockManagerImpl;

public class ExperimentStartApplication {
	final static Logger logger = LoggerFactory.getLogger(ExperimentStartApplication.class);
	public static void main(String[] args) throws InterruptedException {

		IInterface implementation = new IImpl();
		try {
			implementation.startApplication(StockManagerImpl.getInstance());
			logger.debug("Final Report after update/checkout operations");
			StockManagerImpl.getInstance().generateReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
