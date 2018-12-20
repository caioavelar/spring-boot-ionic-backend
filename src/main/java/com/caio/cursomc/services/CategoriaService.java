package com.caio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.cursomc.domain.Categoria;
import com.caio.cursomc.repositories.CategoriaRepository;
import com.caio.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //Instancia automaticamente
	private CategoriaRepository repo;
	
	
	
	public Categoria buscar(Integer id) { //Método que utiliza
										  //um objeto do repositório para pesquisar
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID "+id+" Tipo:"+ Categoria.class.getName()));
	}
}
