package de.tub.fak4.server;

import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.Response;

public class MyAsyncCallBack
implements AsyncCallbackRecipient {
	
	int count;
	int maxCount;
	
	public MyAsyncCallBack() {
		count = 0;
		maxCount = 0;
	}
	
	public MyAsyncCallBack(int _count, int _max) {
		count = _count;
		maxCount = _max;
	}
	
	@Override
	public void callback(Response arg0) {
		// TODO Auto-generated method stub
		
		if (count == maxCount) {
			// log the operation finish
		}
		
	}
	
}
