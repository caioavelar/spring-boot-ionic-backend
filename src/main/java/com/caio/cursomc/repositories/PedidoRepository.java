package com.caio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.cursomc.domain.Pedido;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
}
