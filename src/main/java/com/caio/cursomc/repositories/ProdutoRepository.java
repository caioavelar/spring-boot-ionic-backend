package com.caio.cursomc.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caio.cursomc.domain.Categoria;
import com.caio.cursomc.domain.Produto;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
	
	@Transactional(readOnly=true)
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto>findDistinctByNomeContainingAndCategoriasIn( String nome, List<Categoria> categorias, Pageable pageRequest);
}
