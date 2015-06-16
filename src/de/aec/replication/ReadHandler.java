/**
 *
 */
package de.aec.replication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.IRequestHandler;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;

/**
 */
public class ReadHandler
implements IRequestHandler, AsyncCallbackRecipient {
	
	@Override
	public Response handleRequest(Request req) {
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		System.out.println("The result for Get is" + Storage.getInstance().read(key));
		return new Response(Storage.getInstance().read(key), "Result for Get:", true, req);

	}
	
	@Override
	public boolean requiresResponse() {
		return true;
	}

	@Override
	public boolean hasPriority() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void callback(Response response) {
		if (response.responseCode()) {
			System.out.println("Successfull Read operation");
		}

	}

}
