package de.tub.fak4.aec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * This class tests the CRUD operations
 *
 */
public class Client {
	
	// static String host = "localhost";
	static int port = 5000;
	static InputStream input;

	static Properties property = new Properties();

	public static void main(String[] args)
			throws IOException {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		try {
			input = new FileInputStream("src/resources/ip_assignment.properties");
			property.load(input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> list = new ArrayList<String>();
		list.add("nesrine");
		list.add("yi");
		list.add("cloud");
		
		System.out.println("Running test ");

		Operations.create(property.getProperty("nodeA"), port, "cloudS", list, "nodeA");

		ArrayList<String> read = Operations.read(property.getProperty("nodeA"), port, "cloudS", "nodeA");
		System.out.println("The value is" + read);
		ArrayList<String> newlist = new ArrayList<String>();
		newlist.add("doghri");
		newlist.add("yang");
		newlist.add("aec");
		Operations.update(property.getProperty("nodeA"), port, "cloudS", newlist, "nodeA");
		ArrayList<String> update = Operations.read(property.getProperty("nodeA"), port, "cloudS", "nodeA");
		System.out.println("The value after update is" + update);
		Operations.delete(property.getProperty("nodeA"), port, "cloudS", "nodeA");
		ArrayList<String> delete = Operations.read(property.getProperty("nodeA"), port, "cloudS", "nodeA");
		System.out.println("The value after delete is" + delete);

	}
}
