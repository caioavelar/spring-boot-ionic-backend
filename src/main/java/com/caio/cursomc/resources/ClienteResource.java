package com.caio.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caio.cursomc.domain.Cliente;
import com.caio.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes") //mapeamento
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //Requisição básica
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);
	
	}
	
	

}
