package de.tub.fak4.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edu.kit.aifb.dbe.hermes.Receiver;
import edu.kit.aifb.dbe.hermes.RequestHandlerRegistry;

public class Server {
	
	private static final Logger log = Logger.getLogger(Server.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args)
			throws Exception {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		int port = 9057;
		

		RequestHandlerRegistry reg = RequestHandlerRegistry.getInstance();
		reg.registerHandler("create", new CreateHandler());
		reg.registerHandler("read", new ReadHandler());
		reg.registerHandler("update", new UpdateHander());
		reg.registerHandler("delete", new DeleteHandler());
		Receiver receiver = new Receiver(port);
		
		
		receiver.start();

	}

}
