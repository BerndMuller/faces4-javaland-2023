package de.pdbm.faces4;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseListener;
import jakarta.faces.lifecycle.Lifecycle;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Add and remove SimplePhaseListener. 
 * 
 */
@Named
public class PhaseListenerController {
	
	private static final String LISTENER_NAME = "de.pdbm.faces4.SimplePhaseListener";
	private static final Logger LOGGER = Logger.getLogger(PhaseListenerController.class.getCanonicalName());
	private static final PhaseListener LISTENER;
	
	static {
		PhaseListener listener = null;
		try {
			listener = (PhaseListener) Class.forName(LISTENER_NAME).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LISTENER = (listener == null) ? null : listener;
	}
			
	@Inject
	FacesContext facesContext;

	public PhaseListenerController() {
	}

	public void addPhaseListener() {
		logRegisteredListener();
		Lifecycle lifecycle = facesContext.getLifecycle();
		if (getRegisteredSimplePhaseListener() == null) {
			// register only if not registered allready
			lifecycle.addPhaseListener(LISTENER);		
		}
		logRegisteredListener();
	}

	public void removePhaseListener() {
		logRegisteredListener();
		Lifecycle lifecycle = facesContext.getLifecycle();
		if (getRegisteredSimplePhaseListener() != null) {
			lifecycle.removePhaseListener(getRegisteredSimplePhaseListener());	
		}
		logRegisteredListener();
	}
	
	/**
	 * Get the registered SimplePhaseListener or null.
	 * 
	 * @return registered SimplePhaseListener or null
	 */
	private PhaseListener getRegisteredSimplePhaseListener() {
		Lifecycle lifecycle = facesContext.getLifecycle();
		PhaseListener[] phaseListeners = lifecycle.getPhaseListeners();
		Optional<PhaseListener> first = Stream.of(phaseListeners).filter(pl -> pl.getClass().equals(LISTENER.getClass())).findFirst();
		return first.orElse(null);
	}
	
	private void logRegisteredListener() {
		Lifecycle lifecycle = facesContext.getLifecycle();
		PhaseListener[] phaseListeners = lifecycle.getPhaseListeners();
		if (phaseListeners.length == 0) {
			LOGGER.info("No registered listener");
		} else {
			Stream.of(phaseListeners).forEach(pl -> LOGGER.info("Registered listener: " + pl));	
		}
	}
	
}
