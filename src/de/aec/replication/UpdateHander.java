package de.aec.replication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.IRequestHandler;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;

public class UpdateHander
implements IRequestHandler, AsyncCallbackRecipient {
	
	@Override
	public Response handleRequest(Request req) {
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		System.out.println("Update " + value + " for key " + key);
		Storage.getInstance().update(key, value);
		Response resp = new Response(Storage.getInstance().read(key), "Result for update:", true, req);
		System.out.println("Result for Update is :" + Storage.getInstance().read(key));
		// TODO Forward the request according to the replication path

		return resp;

	}
	
	@Override
	public boolean hasPriority() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean requiresResponse() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void callback(Response response) {
		if (response.responseCode()) {
			System.out.println("Successfull Update operation");
		}
		
	}

}
