package com.br.ng.forum.config.exceptions;

public class AuthorizationException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

	public AuthorizationException(String message){
		super(message);
	}
}
