package com.experiment.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.experiment.interfaces.IStock;
import com.experiment.model.Items;
import com.experiment.utilities.DataToFileWriterUtility;

public class StockManagerImpl implements IStock {

	private static Map<Integer, Items> inventoryMap = new HashMap<>();
	private static Map<Integer, Integer> itemCountMap = new HashMap<>();
	private static StockManagerImpl manager;
	final static Logger logger = LoggerFactory.getLogger(IImpl.class);

	public static synchronized StockManagerImpl getInstance() {
		if (manager == null) {
			manager = new StockManagerImpl();
		}
		return manager;
	}

	@Override
	public void updateItem(Items items, int quantity) {
		if (quantity <= 0) {
			logger.debug("Give quantity 1 or more for item " + items.getSkuId() + " and try adding again");
			return;
		}
		if (itemCountMap.get(items.getSkuId()) != null) {
			itemCountMap.put(items.getSkuId(), itemCountMap.get(items.getSkuId()) + quantity);
			logger.debug("Item with Id " + items.getSkuId() + " is updated in inventory with quantity " + quantity
					+ " New quantity: " + itemCountMap.get(items.getSkuId()));
		} else {
			inventoryMap.put(items.getSkuId(), items);
			itemCountMap.put(items.getSkuId(), quantity);
			logger.debug("Item with Id " + items.getSkuId() + " is added in inventory with quantity " + quantity);
		}
	}

	@Override
	public void checkoutItem(int itemId, int quantity) {
		if (inventoryMap.get(itemId) == null) {
			logger.error("Item doesn't exist in inventory with id " + itemId);
			return;
		}
		if (inventoryMap.get(itemId) != null && itemCountMap.get(itemId) < quantity) {
			logger.error("Item with itemId " + itemId + " is not available for the given quantity " + quantity);
			return;
		}
		if (itemCountMap.get(itemId) == quantity) {
			inventoryMap.remove(itemId);
			itemCountMap.remove(itemId);
			logger.debug(
					"Item with itemId " + itemId + " is checked out with quantity " + quantity + " New quantity: " + 0);
			logger.error("Item with Id " + itemId + " is out of stock from inventory");
			return;
		}
		int newQuantity = itemCountMap.get(itemId) - quantity;
		itemCountMap.put(itemId, newQuantity);
		logger.debug("Item with itemId " + itemId + " is checked out with quantity " + quantity + " New quantity: "
				+ newQuantity);

	}

	@Override
	public void generateReport() {
		StringBuilder report = new StringBuilder();
		if (inventoryMap == null || inventoryMap.keySet() == null) {
			logger.error("No items to generate report");
			return;
		}
		for (int key : inventoryMap.keySet()) {
			logger.debug("Report:- Quantity of Item (Id: " + inventoryMap.get(key).getSkuId() + ", Price: "
					+ inventoryMap.get(key).getProductCost() + ") is Count: " + itemCountMap.get(key));
			report.append("Report:- Quantity of Item (Id: " + inventoryMap.get(key).getSkuId() + ", Price: "
					+ inventoryMap.get(key).getProductCost() + ") is Count: " + itemCountMap.get(key)).append("\n");
		}
		
		writeDataToFile(report.toString());
	}

	private void writeDataToFile(String report) {
		if(report!=null) {
			DataToFileWriterUtility.writeDataToFile(report.toString());
		}else {
			logger.error("No items to generate report");
		}
		
	}

}
