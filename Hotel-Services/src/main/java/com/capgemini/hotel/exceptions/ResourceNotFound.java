package com.capgemini.hotel.exceptions;

public class ResourceNotFound extends RuntimeException{

	public ResourceNotFound() {
		super("Resource not found in hotel database");
	}

	public ResourceNotFound(String message) {
		super(message);
	}
	
}
