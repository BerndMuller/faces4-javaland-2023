package de.pdbm.faces4;

import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * Simple backing bean to be used in programmatic facelet example.
 */
@Named
@RequestScoped
public class BackingBean {
	
	private static final Logger LOGGER = Logger.getLogger(BackingBean.class.getCanonicalName());
			
	private String text;

	public BackingBean() {
	}

	
	public String action() {
		LOGGER.info("Now working with input data: " + text);
		return null;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		System.out.println("eingegeben: " + text);
		this.text = text;
	}

}
