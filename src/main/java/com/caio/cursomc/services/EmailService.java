package com.caio.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.caio.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
