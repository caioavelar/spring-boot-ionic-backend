package com.caio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caio.cursomc.domain.Cliente;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
