package de.aec.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ÍøÂçÅäÖÃ°ïÖúÀà
 *
 * @author Yang Yi
 *
 */
public class ConfigureHelper {

	public static String IP = "127.0.0.1";
	public static int PORT = 8081;
	public static int TIME_OUT = 10000;

	private String selftname;

	public static Map<String, String> servermap = new HashMap<String, String>() {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		
		{
			put("nodeB", "127.0.0.1");
			put("nodeC", "127.0.0.1");
		}
	};

	public String getSelftname() {
		return selftname;
	}

	public void setSelftname(String selftname) {
		this.selftname = selftname;
	}

}
