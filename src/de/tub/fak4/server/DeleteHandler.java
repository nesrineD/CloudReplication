/**
 *
 */
package de.tub.fak4.server;

import java.io.File;
import java.io.IOException;
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
public class DeleteHandler
implements IRequestHandler, AsyncCallbackRecipient {

	@Override
	public Response handleRequest(Request req) {
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		Storage.getInstance().delete(key);
		// TODO Forward the request according to the replication path
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
		config.setMyname(pathID);
		System.out.println("the path ID is " + pathID + "I am node" + config.getMyname());
		
		if (map.containsKey(pathID)) {
			List<String> list = new ArrayList<String>();
			list = map.get(pathID);
			try {
				TransRequestToOtherServers.getInstance().sendRequest("delete", list, req);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean requiresResponse() {
		return false;
	}

	@Override
	public boolean hasPriority() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void callback(Response response) {
		if (response.responseCode()) {
			System.out.println("Successfull Delete operation");
		}

	}

}
