package de.tub.aec.operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.tub.aec.util.ConfigureHelper;
import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;
import edu.kit.aifb.dbe.hermes.Sender;

/**
 * This Class implements the CRUD interface
 *
 */

public class Operations implements CRUD_Interface {

	
	/**
	 * The CRUD interface
	 * 
	 * @param host
	 *            destination IP address
	 * @param post
	 *            destination port number
	 * @param key
	 *            the key of the key value store
	 * @param sender
	 *            the start node ID
	 * @param type
	 *            the type of the communication
	 * @param flag
	 *            this parameter is used by the server to get the pathID 
	 *            only the client sends the path ID with the request
	 * 
	 */
	@Override
	public boolean create(String host, int port, String key, ArrayList<String> value, String sender, String type, Boolean flag) {
		
		Boolean responseCode = true;
		Request request = new Request(key, "create", sender);
		Response response = null;
		request.addItem(value);
		request.addItem(flag);
		Sender send = new Sender(host, port);
		if(ConfigureHelper.SYNC.equals(type)){
			response = send.sendMessage(request, ConfigureHelper.TIME_OUT);
			responseCode = response.responseCode();
		}else{
			responseCode = send.sendMessageAsync(request, new AsyncCallbackRecipient() {
				
				@Override
				public void callback(Response resp) {
					System.out.println("request processed");
				}
				
			});	
		}
		
		return responseCode;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<String> read(String host, int port, String key, String sender, String type, Boolean flag) {
		Request request = new Request(key, "read", sender);
		Response response = null;
		Boolean responseCode = true;
		Sender send = new Sender(host, port);
		if(ConfigureHelper.SYNC.equals(type)){
			response = send.sendMessage(request, ConfigureHelper.TIME_OUT);
			responseCode = response.responseCode();
		}else{
			responseCode = send.sendMessageAsync(request, new AsyncCallbackRecipient() {
				
				@Override
				public void callback(Response resp) {
					System.out.println("read processed");
				}
				
			});	
		}
		
		if (responseCode) {
			return (ArrayList<String>) response.getItems().get(0);
		} else {
			return null;
		}
		
	}
	
	@Override
	public boolean update(String host, int port, String key, ArrayList<String> value, String sender, String type,Boolean flag) {
		
		Response response = null;
		Boolean responseCode = true;
		Request request = new Request(key, "update", sender);
		request.addItem(value);
		Sender send = new Sender(host, port);
		if(ConfigureHelper.SYNC.equals(type)){
			response = send.sendMessage(request, ConfigureHelper.TIME_OUT);
			responseCode = response.responseCode();
		}else{
			responseCode = send.sendMessageAsync(request, new AsyncCallbackRecipient() {
				
				@Override
				public void callback(Response resp) {
					System.out.println("update processed");
				}
				
			});	
		}
		
		return responseCode;
	}

	@Override
	public boolean delete(String host, int port, String key, String sender, String type,Boolean flag) {
		Request req = new Request(key, "delete", sender);
		Sender send = new Sender(host, port);
		if(ConfigureHelper.SYNC.equals(type)){
		   if( send.sendMessage(req, ConfigureHelper.TIME_OUT) != null)
			   return true;
		   else 
			   return false;
			
		}else{
			return send.sendMessageAsync(req, new AsyncCallbackRecipient() {
				
				@Override
				public void callback(Response resp) {
					System.out.println("delete processed");
				}
				
			});	
		}
	}

	
	
	

}
