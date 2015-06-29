package de.tub.aec.operation;

import java.util.ArrayList;
/**
 * The CRUD interface
 * 
 * @param host
 *            destination IP address
 * @param post
 *            destination port number
 * @param key
 *            the key of the key value store
 * @param sender
 *            the start node ID
 * @param type
 *            the type of the communication
 * @param flag
 *            this parameter is used by the server to get the pathID 
 *            only the client sends the path ID with the request
 * 
 */
public interface CRUD_Interface {

	public abstract boolean create(String host, int port, String key, ArrayList<String> value, String sender, String type, Boolean flag);
	
	public abstract ArrayList<String> read(String host, int port, String key, String sender, String type,Boolean flag);
	
	public abstract boolean update(String host, int port, String key, ArrayList<String> value, String sender, String type,Boolean flag);
	
	public abstract  boolean delete(String host, int port, String key, String sender, String type,Boolean flag);
	
}
