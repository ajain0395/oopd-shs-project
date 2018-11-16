package shs;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * 
 *	this class is used for logging exceptions to a text file.
 */
public class Logging {
	
	//static variable to make logger available everywhere.
	public static Logger WRITER;
	FileHandler fileHandler;
	
	public Logging(String className) {
		
		WRITER=Logger.getLogger(className);
		
		try {
			String fileName = className +".log";
			
			File file=new File(fileName);
			fileHandler=new FileHandler(fileName,true);
			WRITER.addHandler(fileHandler);
			WRITER.setUseParentHandlers(false);
			SimpleFormatter simpleFormatter=new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void error(String msg)
	{
		WRITER.setLevel(Level.SEVERE);
		WRITER.severe(msg);
	}
	void warning(String msg)
	{
		WRITER.setLevel(Level.WARNING);
		WRITER.warning(msg);
	}
	void info(String msg)
	{
		WRITER.setLevel(Level.INFO);
		WRITER.info(msg);
	}
	
}