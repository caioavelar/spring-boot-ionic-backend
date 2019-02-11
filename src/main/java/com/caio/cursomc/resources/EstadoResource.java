package com.caio.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caio.cursomc.domain.Cidade;
import com.caio.cursomc.domain.Estado;
import com.caio.cursomc.dto.CidadeDTO;
import com.caio.cursomc.dto.EstadoDTO;
import com.caio.cursomc.services.CidadeService;
import com.caio.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> lista = service.findAllByOrderByNome();
		List<EstadoDTO> listDTO = lista.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}
	//Minha versão
	@RequestMapping(value="/{id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer id){
		List<CidadeDTO> lista = cidadeService.findCidades(id);
	//	List<CidadeDTO> listDTO = lista.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(lista);
	}
	
	/*Versão do professor
	 * @RequestMapping(value="/{id}/cidades", method=RequestMethod.GET)
		public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<CidadeDTO> lista = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDTO = lista.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(lista);
	}
	 * */
	

}
