package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOColecao;
import br.com.viniciusmrosa.modelo.Colecao;

@Service
public class ColecaoServiceImpl implements ColecaoService {

	@Autowired
	private LogCriacaoService logCriacaoService;
	@Autowired
	private DAOColecao daoColecao;
	

	@Override
	public void inserir(Colecao c){
		
		c.setLogCriacao(logCriacaoService.getLogCriacao());
		daoColecao.salva(c);
	}
	
	
	@Override
	public void alterar(Colecao c){
		daoColecao.salva(c);
	}
}
