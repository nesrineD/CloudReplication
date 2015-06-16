package de.aec.replication;

import java.util.List;

import org.apache.log4j.Logger;

import de.aec.util.ConfigureHelper;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;
import edu.kit.aifb.dbe.hermes.Sender;

public class TransRequestToOtherServers {
	
	private static TransRequestToOtherServers instance = null;
	
	private static final Logger log = Logger.getLogger(Storage.class);
	
	public static TransRequestToOtherServers getInstance() {
		
		if (instance == null) {
			instance = new TransRequestToOtherServers();
		}
		
		return instance;
	}
	
	public void sendRequest(String operate, List<String> list, Request req) {
		
		int i = 0;
		while (i < list.size()) {

			if (list.get(i).equals("sync")) {
				String target = list.get(i + 1);
				System.out.println("the synch target is :" + target);

				ConfigureHelper.servermap.get(target);
				Sender sender = new Sender(ConfigureHelper.servermap.get(target), 6000);
				Response r = sender.sendMessage(req, 1000);
				i = i + 2;
				
			} else if (list.get(i).equals("async")) {
				String target = list.get(i + 1);
				System.out.println("the asynch target is :" + target);
				// forward the create request to target server
				// asynchronously
				
				Sender sender = new Sender(ConfigureHelper.servermap.get(target), 6000);
				sender.sendMessageAsync(req, new MyAsyncCallBack());
				i = i + 2;

			} else if (list.get(i).equals("quorum")) {
				i++;
				int qSize = Integer.valueOf(list.get(i));
				int j = i + 1;
				while (!list.get(j).equals("sync") && !list.get(j).equals("async") && !list.get(j).equals(null)) {
					String qparticipant = list.get(j);
					// forward quorum of size qSize to qparticipant
					// extarct the ipaddress and the port number
					// corresponding to the qparticipant
					System.out.println("I sent a quorum of size :" + qSize + "to q participant " + qparticipant);
					Sender sender = new Sender(ConfigureHelper.servermap.get(qparticipant), 6000);
					sender.sendMessageAsync(req, new MyAsyncCallBack());
					j++;

				}
				
			}
		}
	}
}
