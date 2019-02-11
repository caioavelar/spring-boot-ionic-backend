package com.caio.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caio.cursomc.domain.Estado;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
	
	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
