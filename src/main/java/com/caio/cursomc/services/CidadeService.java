package com.caio.cursomc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.cursomc.domain.Cidade;
import com.caio.cursomc.dto.CidadeDTO;
import com.caio.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	//Meu método
	public List<CidadeDTO> findCidades(Integer id){
		
		List<Cidade> lista = repo.findAll();
		List<CidadeDTO> listaDTO = lista.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		List<CidadeDTO> resposta = new ArrayList<>();
		for (CidadeDTO dto : listaDTO) {
			if(dto.getEstado().getId() == id) {
				resposta.add(dto);
			}
		}
		return resposta;
	}
	
	//Método do professor
	/*
	 * public List<Cidade> findByEstado(Integer estadoId){
	 * 	return repo.findCidades(estadoId);
	 * }
	 * 
	 * */
	
}
