package de.aec.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 *
 * @author Yang Yi, Nesrine Doghri
 *
 */
public class ConfigureHelper {

	public static String IP = "127.0.0.1";
	public static int PORT = 8081;
	public static int TIME_OUT = 10000;
	
	private String selftname;

	
		
		
		public Map<String, String> PropertyParser() throws IOException {
			InputStream input = new FileInputStream("src/resources/ip_assignment.properties");
			Properties property = new Properties();
            property.load(input);
            Map<String, String> servermap = new HashMap<String, String>();
            servermap.put("nodeB", property.getProperty("nodeB"));
            servermap.put("nodeC", property.getProperty("nodeC"));
            servermap.put("nodeA", property.getProperty("nodeA"));
            return servermap;
            		
		}

	
	public String getSelftname() {
		return selftname;
	}

	public void setSelftname(String selftname) {
		this.selftname = selftname;
	}

}
