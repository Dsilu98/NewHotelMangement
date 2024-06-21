package com.capgemini.rating.exceptions;

public class ResourceNotFound extends RuntimeException{

	public ResourceNotFound() {
		super("No ratings found");
	}

	public ResourceNotFound(String message) {
		super(message);
	}
	
}
