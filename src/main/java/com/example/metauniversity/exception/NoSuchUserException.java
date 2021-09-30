package com.example.metauniversity.exception;

public class NoSuchUserException extends RuntimeException{

	String returnUrl;
	
    public NoSuchUserException(String message) {
        super(message);
    }
    
    
}
