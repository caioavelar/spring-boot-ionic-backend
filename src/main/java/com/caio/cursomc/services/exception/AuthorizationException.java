package com.caio.cursomc.services.exception;

public class AuthorizationException extends RuntimeException {

	//Classe de exceção personalizada, para casos onde o objeto não for encontrado
	
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
