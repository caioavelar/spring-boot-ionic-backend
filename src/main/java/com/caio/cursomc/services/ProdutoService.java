package com.caio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.caio.cursomc.domain.Categoria;
import com.caio.cursomc.domain.Produto;
import com.caio.cursomc.repositories.CategoriaRepository;
import com.caio.cursomc.repositories.ProdutoRepository;
import com.caio.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired //Instancia automaticamente
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	
	public Produto find(Integer id) { //Método que utiliza
										  //um objeto do repositório para pesquisar
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID "+id+" Tipo:"+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias,pageRequest);
	}
}
