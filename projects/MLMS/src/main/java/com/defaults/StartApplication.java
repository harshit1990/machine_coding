package com.defaults;

import com.defaults.impl.IImpl;
import com.defaults.impl.LMSManager;
import com.defaults.interfaces.IInterface;

public class StartApplication {

	public static void main(String args[]) {
		IInterface in = new IImpl();
		in.startApplication(LMSManager.getInstance());
	}

}
