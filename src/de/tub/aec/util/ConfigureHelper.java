package de.tub.aec.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ������
 * @author Yang Yi
 *
 */
public class ConfigureHelper {
	public static String IP = "141.23.64.204";		// Ŀ������IP
	public static int PORT = 8081;					    // Ŀ�������˿�
	public static int TIME_OUT = 10000;				// ��ʱʱ��10s
	
	public static String SYNC = "sync";
	public static String ASYNC = "async";
	
	public static String SELF_NAME = "nodeA";			//���Ƿ�����A,�������������ֲ�һ��
	
	public static Map<String, String> PropertyParser() throws IOException {
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
