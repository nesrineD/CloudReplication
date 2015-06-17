package de.tub.aec.operation;

import java.util.ArrayList;

public interface CRUD_Interface {

	public abstract boolean create(String host, int port, String key, ArrayList<String> value, String sender, String type, Boolean flag);
	
	public abstract ArrayList<String> read(String host, int port, String key, String sender, String type,Boolean flag);
	
	public abstract boolean update(String host, int port, String key, ArrayList<String> value, String sender, String type,Boolean flag);
	
	public abstract  boolean delete(String host, int port, String key, String sender, String type,Boolean flag);
	
}
