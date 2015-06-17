package de.tub.aec.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {
	
	private HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	
	public void parseXML(File file)
			throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		parsingXML(doc);

	}
	
	public void parsingXML(Document doc) {

		String key = null;
		List<String> oldSet = new ArrayList<String>();

		doc.getDocumentElement().normalize();
//		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("link");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			List<String> attrSet = new ArrayList<String>();

			Node nNode = nList.item(temp);

//			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String src = eElement.getAttribute("src");
//				if (src.equals("nodeC")) {
					key = eElement.getParentNode().getAttributes().item(0).getNodeValue();
//					System.out.println("the  key is " + key);
					String type = eElement.getAttribute("type");
//					System.out.println("the type is  " + type);
					attrSet.add(type);
					
					if (type.equals("sync") || type.equals("async")) {

						String target = eElement.getAttribute("target");
						attrSet.add(target);

//						System.out.println("the target is  " + target);
					} else {
						Integer qsize = Integer.valueOf(eElement.getAttribute("qsize"));
						attrSet.add(qsize.toString());
//						System.out.println("the qsize is  " + qsize);
						NodeList qparticipant = doc.getElementsByTagName("qparticipant");
						for (int i = 0; i < qparticipant.getLength(); i++) {
							Node qNode = qparticipant.item(i);
//							System.out.println("\nCurrent Element :" + qNode.getNodeName());
							String qname = qNode.getAttributes().item(0).getNodeValue();
							attrSet.add(qname);
//							System.out.println("the quorum must be sent to  " + qname);
						}
					}

					if (map.containsKey(key)) {
						attrSet.addAll(oldSet);
						map.put(key, attrSet);
						
					} else {
						oldSet = attrSet;
						map.put(key, attrSet);
					}

				}

//			}

			setMap(map);

		}

	}

	public HashMap<String, List<String>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, List<String>> map) {
		this.map = map;
	}
	
	public void readMap(HashMap<String, List<String>> map) {
//		System.out.println("The map is " + map.toString());
		for (String name : map.keySet()) {

			String key = name.toString();
			String value = map.get(name).toString();
//			System.out.println("the key is " + key + " The value is  " + value);

		}

	}
}
