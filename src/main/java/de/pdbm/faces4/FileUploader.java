package de.pdbm.faces4;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

@Named
@RequestScoped
public class FileUploader {

	private List<Part> files;
	
	public FileUploader() {
	}

	public void upload() {
		for (Part file : files) {
			System.out.println("File to upload: " + (file.getSubmittedFileName()));
		}
	}

	// Getter and Setter
	public List<Part> getFiles() {
		return files;
	}
	public void setFiles(List<Part> files) {
		this.files = files;
	}
	
}
