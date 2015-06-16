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
