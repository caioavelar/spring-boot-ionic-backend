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

import com.caio.cursomc.domain.Categoria;
import com.caio.cursomc.dto.CategoriaDTO;
import com.caio.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") //mapeamento
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //Requisição básica para pegar um valor
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok(obj);
	
	}
	
	@RequestMapping(method=RequestMethod.POST) //Requisição básica para inserir um valor
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //Requisição básica para atualziar um valor
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<CategoriaDTO>> findAll() { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		List <Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET) //Requisição básica
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) { //Método para utilizar o serviço que
															  //Aplica o repositório	
		
		Page <Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok(listDto);
	
	}

}