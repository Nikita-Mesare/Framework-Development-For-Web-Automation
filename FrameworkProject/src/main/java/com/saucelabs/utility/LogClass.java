package com.saucelabs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LogClass {
	
	static Logger log = (Logger) LogManager.getLogger("LogClass");
	
	public static void startTestCase(String sTestCaseName)
	{		  
		 log.info("---------"+sTestCaseName+" test has started");
	}
	
	public static void endTestCase(String sTestCaseName)
	{
		log.info("---------"+sTestCaseName+" test has ended");
	}
	
	public static void info(String message)
	{
		log.info(message);
	}
	
	public static void warn(String message) 
	{
		log.warn(message);
	}

	public static void error(String message)
	{
		 log.error(message);
	}

	public static void fatal(String message) 
	{
		 log.fatal(message);
	}

	public static void debug(String message) 
	{
		 log.debug(message);
	}

}
