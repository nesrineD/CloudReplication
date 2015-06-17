package de.tub.aec.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 帮助类
 * @author Yang Yi
 *
 */
public class ConfigureHelper {
	public static String IP = "141.23.64.204";		// 目标主机IP
	public static int PORT = 8081;					    // 目标主机端口
	public static int TIME_OUT = 10000;				// 超时时间10s
	
	public static String SYNC = "sync";
	public static String ASYNC = "async";
	
	public static String SELF_NAME = "nodeA";			//我是服务器A,不懂服务器名字不一样
	
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
