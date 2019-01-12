package com.caio.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caio.cursomc.domain.Pedido;
import com.caio.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos") //mapeamento
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //Requisição básica
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok(obj);
	
	}
	
	@RequestMapping(method=RequestMethod.POST) //Requisição básica para inserir um valor
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

}
