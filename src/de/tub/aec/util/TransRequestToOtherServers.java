package de.tub.aec.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import de.tub.aec.operation.TaskService;
import edu.kit.aifb.dbe.hermes.Request;

public class TransRequestToOtherServers {
	
	private static TransRequestToOtherServers instance = null;
	
	private static final Logger log = Logger.getLogger(TransRequestToOtherServers.class);
	
	public static TransRequestToOtherServers getInstance() {
		
		if (instance == null) {
			instance = new TransRequestToOtherServers();
		}
		
		return instance;
	}
	
	public void sendRequest(String operate, List<String> list, Request req) throws IOException, InterruptedException, ExecutionException {
		
		int i = 0;
		Future<Boolean> result = null;
		while (i < list.size()) {

			if (list.get(i).equals("sync")) {
				String target = list.get(++i);
				System.out.println("the synch target is :" + target);
				
				//Callable与 Future 
				ExecutorService executor = Executors.newCachedThreadPool();
				TaskService taskService = new TaskService(req, operate, target, ConfigureHelper.SYNC );
				result = executor.submit(taskService);
				System.out.println("taskService运行结果"+result.get());
				if(result.get()){
					//终止
					executor.shutdown();
				}
				i++;
				
			} else if (list.get(i).equals("async")) {
				String target = list.get(++i);
				System.out.println("the asynch target is :" + target);
				// forward the create request to target server
				// asynchronously
				
				//Callable与 Future 
				ExecutorService executor = Executors.newCachedThreadPool();
				TaskService taskService = new TaskService(req, operate, target, ConfigureHelper.ASYNC );
				result = executor.submit(taskService);
				System.out.println("taskService运行结果"+result.get());
				if(result.get()){
					//终止
					executor.shutdown();
				}
				
				i++;

			} else if (list.get(i).equals("quorum")) {
				i++;
				int qSize = Integer.valueOf(list.get(i));
				int j = i + 1;
				while (j < list.size() && !list.get(j).equals("sync") && !list.get(j).equals("async") && !list.get(j).equals(null)) {
					String qparticipant = list.get(j);
					// forward quorum of size qSize to qparticipant
					// extarct the ipaddress and the port number
					// corresponding to the qparticipant
					System.out.println("I sent a quorum of size :" + qSize + "to q participant " + qparticipant);
					
					//Callable与 Future 
					ExecutorService executor = Executors.newCachedThreadPool();
					TaskService taskService = new TaskService(req, operate, qparticipant, ConfigureHelper.ASYNC );
					result = executor.submit(taskService);
					System.out.println("taskService运行结果"+result.get());
					if(result.get()){
						//终止
						executor.shutdown();
					}
					j++;
				}
				i = j;
			}
		}
	}
}
