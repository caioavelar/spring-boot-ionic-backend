package com.caio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.cursomc.domain.Categoria;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
}
