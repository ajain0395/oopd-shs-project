package shs;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import java.util.logging.FileHandler;



public class Logging {
	
	public Logging(String className) {
		// TODO Auto-generated constructor stub

		Logger logger = Logger.getLogger(className);
		
		String fileName = "";
		File fileDes = new File(fileName);
		
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(fileName,true);
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);
			
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
