package com.caio.cursomc.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	//Classe de exceção personalizada, para casos onde o objeto não for encontrado
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
