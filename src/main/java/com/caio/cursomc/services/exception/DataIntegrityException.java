package com.caio.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException {

	//Classe de exceção personalizada, para casos onde o objeto não for encontrado
	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
