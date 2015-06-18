/**
 *
 */
package de.tub.aec.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.tub.aec.util.ConfigureHelper;
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
	private static Logger logger = Logger.getLogger(CreateHandler.class);
	@Override
	public Response handleRequest(Request req) {
		
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		System.out.println(items);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		
		Boolean flag = (Boolean) items.get(2);
		logger.info("I am the server: "+ConfigureHelper.SELF_NAME);
	
		Storage.getInstance().create(key, value);
		Response resp = new Response(Storage.getInstance().read(key), "Result for create:", true, req);
//		Response resp = new Response(key, "Result for create:", true, req);
		
		
		String pathID = req.getOriginator();
		
		//to check that it is initialized by the client
		//only the client sends a third argument
		if(flag){
			try {
				//Call Farwarding 
				StrategyHandler.runningStrategy(pathID, req,"create");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
