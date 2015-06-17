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
public class DeleteHandler
implements IRequestHandler, AsyncCallbackRecipient {
	private static Logger logger = Logger.getLogger(DeleteHandler.class);
	@Override
	public Response handleRequest(Request req) {
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		
		Boolean flag = (Boolean) items.get(2);
		logger.info("I am the server: "+ConfigureHelper.SELF_NAME);
		//���ش洢����
		Response resp = new Response(Storage.getInstance().delete(key), "Result for delete:", true, req);
		
		//���͸��ĸ�������
		String sender = req.getOriginator();
		
		//�Ƿ��ɿͻ��˷���
		if(flag){
			try {
				//����ת������
				StrategyHandler.runningStrategy(sender, req, "delete");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resp;
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
