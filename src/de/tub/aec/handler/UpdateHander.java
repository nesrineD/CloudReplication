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

public class UpdateHander
implements IRequestHandler, AsyncCallbackRecipient {
	private static Logger logger = Logger.getLogger(UpdateHander.class);
	@Override
	public Response handleRequest(Request req) {
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		Boolean flag = (Boolean) items.get(2);
		logger.info("I am the server: "+ConfigureHelper.SELF_NAME);
		//本地存储数据
		Storage.getInstance().update(key, value);
		Response resp = new Response(Storage.getInstance().read(key), "Result for create:", true, req);
//		Response resp = new Response(key, "Result for create:", true, req);
		
		//发送给哪个服务器
		String sender = req.getOriginator();
		
		//是否由客户端发起
		if(flag){
			try {
				//调用转发策略
				StrategyHandler.runningStrategy(sender, req,"update");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
