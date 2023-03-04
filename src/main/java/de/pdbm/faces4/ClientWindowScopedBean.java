package de.pdbm.faces4;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.lifecycle.ClientWindowScoped;
import jakarta.inject.Named;


/**
 * Example to demonstrate the new client window scope. 
 */
@Named
@ClientWindowScoped
public class ClientWindowScopedBean implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(ClientWindowScopedBean.class.getCanonicalName());
	
	private final int state;
	
	public ClientWindowScopedBean() {
		state = new Random().nextInt();
	}

	public int getState() {
		return state;
	}

	@PostConstruct
	public void init() {
		LOGGER.info("created");
		
	}
	
	/**
	 * Is not called. Session attribute destroyed if session terminates. See ClientWindowScopeContextManager.
	 */
	@PreDestroy
	public void cleanUp() {
		LOGGER.info("about to destroy");
	}
	
}
