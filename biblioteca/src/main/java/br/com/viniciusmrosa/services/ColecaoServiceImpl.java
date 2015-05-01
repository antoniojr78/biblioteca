package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOColecao;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.ImpossivelApagarRegistroException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.Colecao;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;

@Service
public class ColecaoServiceImpl extends AbstractService implements ColecaoService {

	@Autowired
	private LogCriacaoService logCriacaoService;
	@Autowired
	private DAOColecao daoColecao;	
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoRegistroSecurityService ;
	
	private Colecao colecaoAlterar;
	@Override
	public void inserir(Colecao c){
		
		//c.setLogCriacao(logCriacaoService.getLogCriacao());
		daoColecao.salva(c);
	}
	
	
	@Override
	public void alterar(Colecao c) throws ErroOperacaoBDException,NegocioException{
		
		colecaoAlterar = daoColecao.getById(c.getId());
		checaExistente(colecaoAlterar);
		checaPermissoes(colecaoAlterar);
		colecaoAlterar.setNome(c.getNome());
		daoColecao.salva(colecaoAlterar);

		
	}
	private void checaPermissoes(Colecao c) throws NegocioException{
		if(!alteracaoRegistroSecurityService.podeAlterar(c)){
			throw new PermissaoAlteracaoNegadaException();
		}
	}
	private void checaExistente(Colecao c) throws RegistroNaoEncontradoException{
		if(c==null){
			throw new RegistroNaoEncontradoException();
		}
	}
	@Override
	public void excluir(Long id) throws NegocioException,
			ErroOperacaoBDException {
		colecaoAlterar = daoColecao.getById(id);
		regrasBasicas(colecaoAlterar);
		try {
			
			daoColecao.deleta(colecaoAlterar );
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelApagarRegistroException();
		}
		
	}
}
