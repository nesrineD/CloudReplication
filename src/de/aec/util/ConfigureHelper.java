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
	public static int PORT = 4000;
	public static int TIME_OUT = 1000;
	
	private String myname;

	
		
		
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

	
	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

}
