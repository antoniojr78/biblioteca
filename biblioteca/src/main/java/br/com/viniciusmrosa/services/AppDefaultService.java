package br.com.viniciusmrosa.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viniciusmrosa.modelo.LogCriacao;
import br.com.viniciusmrosa.security.SecurityUtils;

public abstract class AppDefaultService {
	
	@Autowired
	private SecurityUtils securityUtils;
	
	public LogCriacao getLogCriacao(){
		
		LogCriacao log = new LogCriacao();
		log.setDataCriacao(Calendar.getInstance().getTime());
		System.out.println("usuario logado:" +securityUtils.buscaUsuarioLogado().getId() +  "  " + securityUtils.buscaUsuarioLogado().getNome() );
		log.setUsuarioCriacao(securityUtils.buscaUsuarioLogado());
		
		return log;
	}
}
