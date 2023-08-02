package com.lwcd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    //without constructor exception
    public ResourceNotFoundException()
    {
        super("Resource Not Found on server!!");
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
