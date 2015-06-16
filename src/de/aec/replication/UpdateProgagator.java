package de.aec.replication;

import org.apache.log4j.Logger;

public class UpdateProgagator {

	private static final Logger log = Logger.getLogger(UpdateProgagator.class);

	public void QuorumPutPropagator() {
		// Callable<Responsr>
		// the sending is in the call method and it return the reponse
		// we need to cal this method for each quorum participant
		// sender.sendMessageAsync(req, createHandler); returns a boolean not a
		// response
		
		// for (all the qparticipants) {
		// Callable<Sender> worker = new MyCallable();
		// Future<Long> submit = executor.submit(worker); -- for the execution
		
	}
}
