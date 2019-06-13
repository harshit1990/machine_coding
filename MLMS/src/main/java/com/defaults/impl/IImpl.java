package com.defaults.impl;

import com.defaults.interfaces.IInterface;

public class IImpl implements IInterface {

	@Override
	public void startApplication(LMSManager manager) {
		Thread t1 = new Thread(() -> {
			System.out.println("\n=====================================Student Creation========================\n");
			manager.createStudentInstance(1, "harshit mishra", "mishra.harshit1990@gmail.com");
			manager.createStudentInstance(2, "kavi dayal", "kavidayal@gmail.com");
			manager.createStudentInstance(3, "ram kumar", "ramkumka@gmail.com");
			manager.createStudentInstance(4, "satish sahu", "sahusatish@gmail.com");
			manager.createStudentInstance(5, "shiv kumar bera", "beraShivKumar@gmail.com");
			manager.createStudentInstance(1, "harshit mishra", "mishra.harshit1990@gmail.com");

			System.out.println("\n=====================================Book  Registration========================\n");

			manager.createBookInstance(1, "R.L.Stevenson", "Treasure Island");
			manager.createBookInstance(2, "xyz", " Island grove");
			manager.createBookInstance(3, "shakespere", "Hamlet");
			manager.createBookInstance(4, "shakespere", "twelfth night");
			manager.createBookInstance(5, "R.L.Stevenson", "coconut grove");
			manager.createBookInstance(6, "Amish", "shiva legacy");
			manager.createBookInstance(7, "Amish", "sita legacy");
			manager.createBookInstance(8, "H.C.verma", "Physics");
			
			System.out.println("\n=====================================lend Books========================\n");
			
			manager.lentBook(1, 1);
			manager.lentBook(2, 1);
			manager.lentBook(1, 2);
			manager.lentBook(2, 8);
			
			manager.lentBook(2, 8);
			manager.lentBook(2, 8);
			
			
			System.out.println("\n=====================================Return Books========================\n");
			manager.recieveBook(1, 1);
			manager.recieveBook(2, 1);
			manager.recieveBook(2, 8);
			
			

		});
		t1.start();
	}

}
