/**
 *
 */
package de.aec.replication;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.aec.util.ConfigureHelper;
import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.IRequestHandler;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;

/**
 *
 *
 *
 */
public class CreateHandler
		implements IRequestHandler, AsyncCallbackRecipient {
	
	private static CreateHandler createHandler = new CreateHandler();

	@Override
	public Response handleRequest(Request req) {
		
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		System.out.println(" The value is " + value + " for key " + key);
		Storage.getInstance().create(key, value);
		Response resp = new Response(Storage.getInstance().read(key), "Result for create:", true, req);
		System.out.println("Result for create is :" + Storage.getInstance().read(key));
		
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		File file = new File("src/resources/ReplicationPath.xml");
		ParseXML parse = new ParseXML();
		try {
			parse.parseXML(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = parse.getMap();
		String pathID = req.getOriginator();

		ConfigureHelper config = new ConfigureHelper();
		config.setSelftname(pathID);
		System.out.println("the path ID is " + pathID + "I am node" + config.getSelftname());
		
		if (map.containsKey(pathID)) {
			List<String> list = new ArrayList<String>();
			list = map.get(pathID);
			TransRequestToOtherServers.getInstance().sendRequest("create", list, req);
		}
		return resp;
	}
	
	@Override
	public boolean requiresResponse() {
		return true;
	}
	
	@Override
	public boolean hasPriority() { // TODO Auto-generated method
		return false;
	}
	
	@Override
	public void callback(Response response) {
		if (response.responseCode()) {
			System.out.println("Successfull operation");
		}
		
	}
}
