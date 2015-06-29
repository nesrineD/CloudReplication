package de.tub.aec.operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import de.tub.aec.util.ConfigureHelper;
import edu.kit.aifb.dbe.hermes.Request;

/**
 * 
 *
 */
public class TaskService implements Callable<Boolean> {
	/**
	 * @param request
	 *            the request
	 * @param operate
	 *            the CRUD operation
	 * @param target
	 *            the destination
	 * @param type
	 *            the type of replication: sync, async or quorum
	 *
	 */

	private Request request;
	private String operate;
	private String target;
	private String type;

	public TaskService() {

	}

	public TaskService(Request request, String operate, String target,
			String type) {
		this.request = request;
		this.operate = operate;
		this.target = target;
		this.type = type;
	}

	/**
	 * This method is responsible for executing the proper operations
	 * 
	 * @return true if he operation succeeded, false otherwise
	 *
	 */

	@Override
	public Boolean call() throws Exception {
		List<Serializable> items = new ArrayList<Serializable>();
		items = request.getItems();
		String key = (String) items.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		Boolean responseCode = false;

		if ("create".equals(operate)) {
			Operations op = new Operations();
			responseCode = op.create(
					ConfigureHelper.PropertyParser().get(target),
					ConfigureHelper.PORT, key, value, target, type,
					Boolean.FALSE);
		}

		if ("update".equals(operate)) {
			Operations op = new Operations();
			responseCode = op.update(
					ConfigureHelper.PropertyParser().get(target),
					ConfigureHelper.PORT, key, value, target, type,
					Boolean.FALSE);
		}

		if ("delete".equals(operate)) {
			Operations op = new Operations();
			responseCode = op.delete(
					ConfigureHelper.PropertyParser().get(target),
					ConfigureHelper.PORT, key, target, type, Boolean.FALSE);
		}

		return responseCode;
	}

}
