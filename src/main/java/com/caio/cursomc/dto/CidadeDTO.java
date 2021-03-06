package com.caio.cursomc.dto;

import java.io.Serializable;

import com.caio.cursomc.domain.Cidade;
import com.caio.cursomc.domain.Estado;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Estado estado;
	
	public CidadeDTO() {
		
	}
	
	public CidadeDTO(Cidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.estado = obj.getEstado();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
