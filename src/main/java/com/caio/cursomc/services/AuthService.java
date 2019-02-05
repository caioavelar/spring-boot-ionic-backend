package com.caio.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.caio.cursomc.domain.Cliente;
import com.caio.cursomc.repositories.ClienteRepository;
import com.caio.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {
	
	private Random randi = new Random();;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass); 
		
		
	}
	private String newPassword() {
		char [] vet = new char[10];
		for(int i=0;i<10;i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	private char randomChar() {
		int opt = randi.nextInt(3);
		if(opt==0) {
			return (char) (randi.nextInt(10) +48);
		} //gera digito
		else if(opt==1) { //gera maiscula
			return (char) (randi.nextInt(26) +65);
		}
		else{ //gera minuscula
			return (char) (randi.nextInt(26) +97);
		}
		}
	 
}
