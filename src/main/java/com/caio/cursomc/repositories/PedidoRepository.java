package com.caio.cursomc.repositories;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.cursomc.domain.Cliente;
import com.caio.cursomc.domain.Pedido;

@Repository //Anotação de repositório						//Tipo de objeto, tipo do identificador
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	//Interface, classe que não pode ser instanciada diretamente
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
