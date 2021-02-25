package com.br.ng.forum.config.exceptions;

public class BadRequestException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

	public BadRequestException(String message){
		super(message);
	}
}
