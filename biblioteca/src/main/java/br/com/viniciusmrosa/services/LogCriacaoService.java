package br.com.viniciusmrosa.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.modelo.LogCriacao;
import br.com.viniciusmrosa.security.SecurityUtils;

@Service
public class LogCriacaoService {
	
	@Autowired
	private SecurityUtils securityUtils ;
	
	public LogCriacao getLogCriacao(){

		return new LogCriacao(securityUtils.buscaUsuarioLogado(),Calendar.getInstance().getTime());
	}
}
