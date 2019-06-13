import org.apache.log4j.Logger;

public class DriverClass {

	private static final Logger log = Logger.getLogger(DriverClass.class);
	public static void main(String[] args) {
		try {
			new DriverClass().performTransaction(InventoryManager.getInstance());
			log.debug("Final Report");
			InventoryManager.getInstance().generateReport();
		} catch (Exception e) {
			log.error("Error in executing inventory transaction ", e);
		}
	}
	
	public void performTransaction(InventoryManager manager) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			manager.updateItem(new Item(1,"Pen",34.44), 10);
			manager.updateItem(new Item(2,"Shoe",30.09), 10);
			manager.updateItem(new Item(1,"Pen",34.44), 3);
			manager.updateItem(new Item(2,"Shoe",30.09), 3);
		});
		
		Thread t2 = new Thread(() -> {
			manager.checkoutItem(1, 10);
			manager.checkoutItem(2, 5);
			manager.checkoutItem(2, 5);
			manager.checkoutItem(1, 5);
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
