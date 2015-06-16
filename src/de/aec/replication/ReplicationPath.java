package de.aec.replication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This Class store the replication path is a Map
 */
public class ReplicationPath {

	private Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	
	public Map<String, Set<String>> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Set<String>> map) {
		this.map = map;
	}
	
	public void TargetSet() {
		Set<String> targets = new HashSet<String>();
		targets.add("SyncTarget");
		targets.add("nodeB");
		map.put("nodeA", targets);
	}
}
