package com.experiment.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataToFileWriterUtility {

	private static final String FILENAME = "/Users/harshitmishra/Desktop/inventory.txt";

	public static void writeDataToFile(String content) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
