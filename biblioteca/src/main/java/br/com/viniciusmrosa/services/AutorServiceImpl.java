package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.modelo.Autor;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;

@Service("autorService")
public class AutorServiceImpl extends AbstractService implements AutorService {

	
	@Autowired
	private LogCriacaoService logCriacaoService;
	
	@Autowired
	private DAOAutor daoAutor;
	
	private Autor autorAlteracao;
	

	@Override
	public void alterar(Autor autor) throws NegocioException,
			ErroOperacaoBDException {
		
		autorAlteracao = daoAutor.getById(autor.getId());
		
		checaExistente(autorAlteracao);
		checaPermissoes(autorAlteracao);
		
		autorAlteracao.setNome(autor.getNome());
		daoAutor.salva(autorAlteracao);
		
	}

	@Override
	public void excluir(Long id) throws NegocioException,
			ErroOperacaoBDException {
		autorAlteracao = daoAutor.getById(id);
		checaExistente(autorAlteracao);
		checaPermissoes(autorAlteracao);
		
		daoAutor.deleta(autorAlteracao);
	}


	@Override
	public void inserir(Autor autor) {
		autor.setLogCriacao(logCriacaoService.getLogCriacao());
		daoAutor.salva(autor);
		
	}


	
		
}
