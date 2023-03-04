package de.pdbm.faces4;

import java.util.logging.Logger;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

/**
 * Simple phase listener logging phases.
 * 
 */
@SuppressWarnings("serial")
public class SimplePhaseListener implements PhaseListener {
	
	private static final Logger LOGGER = Logger.getLogger(SimplePhaseListener.class.getCanonicalName());
	
	@Override
	public void afterPhase(PhaseEvent pe) {
		LOGGER.info("after " + pe.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent pe) {
		LOGGER.info("before " + pe.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
