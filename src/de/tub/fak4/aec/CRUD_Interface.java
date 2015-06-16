package de.tub.fak4.aec;

import java.util.ArrayList;

public interface CRUD_Interface {

	public static boolean create(String host, int port, String key, ArrayList<String> value) {
		return false;
	}
	
	public static ArrayList<String> read(String host, int port, String key) {
		return null;
	}
	
	public static boolean update(String host, int port, String key, ArrayList<String> value) {
		return false;
	}
	
	public static void delete(String host, int port, String key) {}
	
}
