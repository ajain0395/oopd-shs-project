package shs;

import java.io.IOException;
import java.util.logging.*;

/**
 * 
 *	this class is used for logging exceptions and informations to a text file.
 */
public class Logging {
	
	//static variable to make logger available everywhere.
	public static Logger WRITER;
	public String className;
	
	public Logging(String className) {
		
		this.className = className;
		WRITER=Logger.getLogger(className);
		FileHandler fileHandler;

		try {
			String fileName = className +".log";
		//	File file=new File(fileName);
			fileName = "./logs/" + fileName;
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
		msg = className + " " + msg;
		WRITER.setLevel(Level.SEVERE);
		WRITER.severe(msg);
	}
	void warning(String msg)
	{
		msg = className + " " + msg;
		WRITER.setLevel(Level.WARNING);
		WRITER.warning(msg);
	}
	void info(String msg)
	{
		msg = className + " " + msg;
		WRITER.setLevel(Level.INFO);
		WRITER.info(msg);
	}
	
}