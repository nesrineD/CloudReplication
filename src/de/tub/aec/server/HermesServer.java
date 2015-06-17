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

public class HermesServer {
	private static RequestHandlerRegistry req = null;
	// log4j初始化
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
		SimpleFileLogger.getInstance();
		req = RequestHandlerRegistry.getInstance();
	}
	
	// 获取RequestHandlerRegistry实例
	public static RequestHandlerRegistry getRequestHandlerRegistry() {
		if(req == null) {
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
		int port = 8081;
		try {
			
			receiver = new Receiver(port, 3, 3);
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
