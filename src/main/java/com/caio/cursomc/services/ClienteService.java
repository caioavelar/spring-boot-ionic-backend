package com.caio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caio.cursomc.domain.Cidade;
import com.caio.cursomc.domain.Cliente;
import com.caio.cursomc.domain.Endereco;
import com.caio.cursomc.domain.enums.TipoCliente;
import com.caio.cursomc.dto.ClienteDTO;
import com.caio.cursomc.dto.ClienteNewDTO;
import com.caio.cursomc.repositories.ClienteRepository;
import com.caio.cursomc.repositories.EnderecoRepository;
import com.caio.cursomc.services.exception.DataIntegrityException;
import com.caio.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //Instancia automaticamente
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) { //Método que utiliza
										  //um objeto do repositório para pesquisar
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID "+id+" Tipo:"+ Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj=repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid  = new Cidade(objDto.getCidadeId(),null,null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEndereco().add(end);
		cli.getTelefone().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null ) {
			cli.getTelefone().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefone().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	
}
