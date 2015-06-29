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

/**
 * This class parses the Replication.xml file and stores the values in a map The
 * key of this map is the pathID if a node receives the request then it extracts
 * the pathID and the corresponding targets
 *
 */
public class ParseXML {

	/**
	 * This method builds a document out of a file
	 * 
	 * @param map
	 *            the map where the part of the replication path with source the
	 *            node id will be stored
	 *
	 */

	private HashMap<String, List<String>> map = new HashMap<String, List<String>>();

	public void parseXML(File file) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		parsingXML(doc);

	}

	/**
	 * This method stores the elements retrieved from a document in a map
	 *
	 */

	public void parsingXML(Document doc) {

		String key = null;
		List<String> oldSet = new ArrayList<String>();

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("link");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			List<String> attrSet = new ArrayList<String>();

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String src = eElement.getAttribute("src");
				if (src.equals(ConfigureHelper.SELF_NAME)) {
					key = eElement.getParentNode().getAttributes().item(0)
							.getNodeValue();

					String type = eElement.getAttribute("type");
					attrSet.add(type);

					if (type.equals("sync") || type.equals("async")) {

						String target = eElement.getAttribute("target");
						attrSet.add(target);

					} else {
						Integer qsize = Integer.valueOf(eElement
								.getAttribute("qsize"));
						attrSet.add(qsize.toString());
						NodeList qparticipant = doc
								.getElementsByTagName("qparticipant");
						for (int i = 0; i < qparticipant.getLength(); i++) {
							Node qNode = qparticipant.item(i);
							String qname = qNode.getAttributes().item(0)
									.getNodeValue();
							attrSet.add(qname);
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

			}

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

		for (String name : map.keySet()) {

			String key = name.toString();
			String value = map.get(name).toString();
			System.out.println("the key is " + key + " The value is  " + value);

		}

	}
}
