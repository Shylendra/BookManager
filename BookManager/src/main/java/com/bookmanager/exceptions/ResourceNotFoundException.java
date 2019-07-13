package com.bookmanager.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4674976740233313642L;
	
	private Integer resourceId;
	 
    public ResourceNotFoundException(Integer resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

}
