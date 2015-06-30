package de.tub.aec.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class is used to store the informations of the property file into a map
 *
 */

public class ConfigureHelper {

	public static int TIME_OUT = 1000;

	public static String SYNC = "sync";
	public static String ASYNC = "async";
	
	public static String SELF_NAME = "nodeC";

	/**
	 * Stores the ip addresses, nodeId mapping in a map
	 *
	 * @return the server map: key= node ID, value=private IP address
	 */
	
	public static Map<String, String> PropertyParser()
			throws IOException {
		InputStream input = new FileInputStream("resources/ip_assignment.properties");
		Properties property = new Properties();
		property.load(input);
		Map<String, String> servermap = new HashMap<String, String>();
		servermap.put("nodeB", property.getProperty("nodeB"));
		servermap.put("nodeC", property.getProperty("nodeC"));
		servermap.put("nodeA", property.getProperty("nodeA"));
		return servermap;

	}

}
