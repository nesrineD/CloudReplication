package de.tub.fak4.client;

import java.util.ArrayList;

import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;
import edu.kit.aifb.dbe.hermes.Sender;

/**
 *
 *
 *
 */
public class Operations
implements CRUD_Interface {

	// private static Operations operations = new Operations();

	public static boolean create(String host, int port, String key, ArrayList<String> value, String myID) {
		
		Request request = new Request(key, "create", myID);
		request.addItem(value);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, 1000);
		return response.responseCode();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> read(String host, int port, String key, String myID) {
		Request request = new Request(key, "read", myID);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, 1000);
		
		if (response.responseCode()) {
			return (ArrayList<String>) response.getItems().get(0);
		} else {
			return null;
		}
		
	}
	
	public static boolean update(String host, int port, String key, ArrayList<String> value, String myID) {
		
		Request request = new Request(key, "update", myID);
		request.addItem(value);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, 1000);
		return response.responseCode();
	}

	public static void delete(String host, int port, String key, String myID) {
		Request req = new Request(key, "delete", myID);
		Sender s = new Sender(host, port);
		s.sendMessage(req, 1000);
	}
	
	

}
