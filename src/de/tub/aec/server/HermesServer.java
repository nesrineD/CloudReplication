package de.tub.aec.server;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import de.tub.aec.handler.CreateHandler;
import de.tub.aec.handler.DeleteHandler;
import de.tub.aec.handler.ReadHandler;
import de.tub.aec.handler.UpdateHander;
import edu.kit.aifb.dbe.hermes.Receiver;
import edu.kit.aifb.dbe.hermes.RequestHandlerRegistry;
import edu.kit.aifb.dbe.hermes.SimpleFileLogger;

/**
 * The main Server Class It registers the handlers and starts
 *
 */
public class HermesServer {

	private static RequestHandlerRegistry req = null;

	static {
		PropertyConfigurator.configure("resources/log4j.properties");
		SimpleFileLogger.getInstance();
		req = RequestHandlerRegistry.getInstance();
	}

	public static RequestHandlerRegistry getRequestHandlerRegistry() {
		if (req == null) {
			req = RequestHandlerRegistry.getInstance();
		}
		return req;
	}

	public static void main(String[] args) {
		CreateHandler ch = new CreateHandler();
		UpdateHander uh = new UpdateHander();
		ReadHandler rh = new ReadHandler();
		DeleteHandler dh = new DeleteHandler();
		
		HermesServer.getRequestHandlerRegistry().registerHandler("create", ch);
		HermesServer.getRequestHandlerRegistry().registerHandler("update", uh);
		HermesServer.getRequestHandlerRegistry().registerHandler("read", rh);
		HermesServer.getRequestHandlerRegistry().registerHandler("delete", dh);
		
		Receiver receiver = null;
		int port = 5000;
		try {
			
			receiver = new Receiver(port, 5, 5);
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
