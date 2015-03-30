package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.modelo.Autor;

@Service("autorService")
public class AutorServiceImpl implements AutorService {

	
	@Autowired
	private LogCriacaoService logCriacaoService;
	
	@Autowired
	private DAOAutor daoAutor;
	
	@Override
	public void inserirAutor(Autor autor) {		
		autor.setLogCriacao(logCriacaoService.getLogCriacao());
		daoAutor.salva(autor);
	}

		
}
