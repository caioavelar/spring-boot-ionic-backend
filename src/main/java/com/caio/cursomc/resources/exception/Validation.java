package com.caio.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class Validation extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors= new ArrayList<>();
	
	public Validation(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName,messagem) );
	}

}
