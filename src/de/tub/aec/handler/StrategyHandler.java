package de.tub.aec.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.tub.aec.util.ParseXML;
import de.tub.aec.util.TransRequestToOtherServers;
import edu.kit.aifb.dbe.hermes.Request;

public class StrategyHandler {

	public static void runningStrategy(String sender, Request request, String operation) throws Exception{
		
	
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		File file = new File("resources/ReplicationPath.xml");
		ParseXML parse = new ParseXML();
		parse.parseXML(file);
		
		map = parse.getMap();
		System.out.println("###################: map "+map);
		
		if (map.containsKey(sender)) {
			List<String> list = new ArrayList<String>();
			list = map.get(sender);
			
			if (operation.equals("create")){
			
				TransRequestToOtherServers.getInstance().sendRequest("create", list, request);
			}
			else if (operation.equals("update")){
			
				TransRequestToOtherServers.getInstance().sendRequest("update", list, request);
			}
			else if (operation.equals("read")){
				
				TransRequestToOtherServers.getInstance().sendRequest("read", list, request);
			}
			else {
				
				TransRequestToOtherServers.getInstance().sendRequest("delete", list, request);
			}
			
		}
		
	}
}
