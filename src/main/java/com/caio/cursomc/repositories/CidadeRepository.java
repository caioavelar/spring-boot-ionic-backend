package com.caio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.cursomc.domain.Cidade;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
	
	//implementação do professor
	//Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	//public List<Cidade> findCidades(@Param("estado_Id)Integer estado_id); 
	
}
