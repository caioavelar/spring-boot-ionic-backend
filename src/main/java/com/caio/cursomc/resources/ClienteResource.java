package com.caio.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caio.cursomc.domain.Cliente;
import com.caio.cursomc.dto.ClienteDTO;
import com.caio.cursomc.dto.ClienteNewDTO;
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //Requisição básica para atualziar um valor
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj=service.update(obj);
		return ResponseEntity.noContent().build(); 
		
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE) //Requisição básica para deletar um valor
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET) //Requisição básica
	public ResponseEntity<List<ClienteDTO>> findAll() { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		List <Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET) //Requisição básica
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		Page <Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok(listDto);
	
	}
	
	
	@RequestMapping(method=RequestMethod.POST) //Requisição básica para inserir um valor
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	

}
