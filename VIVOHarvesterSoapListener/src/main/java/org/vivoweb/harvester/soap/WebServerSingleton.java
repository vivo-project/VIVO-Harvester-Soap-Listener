package org.vivoweb.harvester.soap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quickserver.util.logging.SimpleTextFormatter;

// WebServerSingleton
// This is the Singleton Object to store and access the webserver config
// properties . This is based on the singleton pattern and a new Object
// will be created only when you restart the server
public class WebServerSingleton {

	private static HashMap<String, String> propertyMap = new HashMap<String, String>();
	final static Logger logger = Logger.getLogger(VIVOWebServicesListener.class
			.getName());
	private final static WebServerSingleton INSTANCE = new WebServerSingleton();

	// No one else can create a Object .. since constructor is private
	private WebServerSingleton() {
		
	

		FileHandler txtLog = null;
		File log = new File("./log/");
		if (!log.canRead())
			log.mkdir();
		try {
			txtLog = new FileHandler("log/Soap-bizz-talk%u%g.txt", 1024 * 1024, 20,
					true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtLog.setFormatter(new SimpleTextFormatter());
		txtLog.setLevel(Level.FINE);
		logger.addHandler(txtLog);
		logger.fine("From singleton");
	}

	public static WebServerSingleton getInstance() {
		return INSTANCE;
	}

	public static HashMap<String, String> getpropertyMap() {
		return propertyMap;
	}

	public static String getProperty(String name) {
		return propertyMap.get(name);
	}

	public static String setProperty(String name, String value) {
		return propertyMap.put(name, value);
	}
}