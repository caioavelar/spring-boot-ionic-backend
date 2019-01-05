package com.caio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.cursomc.domain.Pedido;
import com.caio.cursomc.repositories.PedidoRepository;
import com.caio.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired //Instancia automaticamente
	private PedidoRepository repo;
	
	
	
	public Pedido find(Integer id) { //Método que utiliza
										  //um objeto do repositório para pesquisar
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID "+id+" Tipo:"+ Pedido.class.getName()));
	}
}
