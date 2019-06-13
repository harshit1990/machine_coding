import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class InventoryManager {

	private static Map<Integer, Item> inventoryMap = new HashMap<>();
	private static Map<Integer, Integer> itemCountMap = new HashMap<>();
	private static final Logger log = Logger.getLogger(InventoryManager.class);
	private static InventoryManager manager;
	
	private InventoryManager() {
		
	}
	
	public static synchronized InventoryManager getInstance() {
		if(manager == null) {
			manager = new InventoryManager();
		}
		return manager;
	}

	public void updateItem(Item item, int quantity) {
		if (quantity <= 0) {
			log.error("Give quantity 1 or more for item " + item.getId() + " and try adding again");
			return;
		}
		if (itemCountMap.get(item.getId()) != null) {
			itemCountMap.put(item.getId(), itemCountMap.get(item.getId()) + quantity);
			log.debug("Item with Id " + item.getId() + " is updated in inventory with quantity " + quantity
					+ " New quantity: " + itemCountMap.get(item.getId()));
		} else {
			inventoryMap.put(item.getId(), item);
			itemCountMap.put(item.getId(), quantity);
			log.debug("Item with Id " + item.getId() + " is added in inventory with quantity " + quantity);
		}
	}

	public synchronized void checkoutItem(int itemId, int quantity) {
		if (inventoryMap.get(itemId) == null) {
			log.fatal("Item doesn't exist in inventory with id " + itemId);
			return;
		}
		if (inventoryMap.get(itemId) != null && itemCountMap.get(itemId) < quantity) {
			log.error("Item with itemId " + itemId + " is not available for the given quantity " + quantity);
			return;
		}
		if (itemCountMap.get(itemId) == quantity) {
			inventoryMap.remove(itemId);
			itemCountMap.remove(itemId);
			log.debug("Item with itemId " + itemId + " is checked out with quantity " + quantity + " New quantity: "
					+ 0);
			log.fatal("Item with Id " + itemId + " is out of stock from inventory");
			return;
		}
		int newQuantity = itemCountMap.get(itemId) - quantity;
		itemCountMap.put(itemId, newQuantity);
		log.debug("Item with itemId " + itemId + " is checked out with quantity " + quantity + " New quantity: "
				+ newQuantity);
	}

	public void generateReport() {
		if (inventoryMap == null || inventoryMap.keySet() == null) {
			log.error("No items to generate report");
			return;
		}
		for (int key : inventoryMap.keySet()) {
			log.debug("Report:- Quantity of Item (Id: " + inventoryMap.get(key).getId() + ", Price: "
					+ inventoryMap.get(key).getCost() + ") is Count: " + itemCountMap.get(key));
		}
	}

}
