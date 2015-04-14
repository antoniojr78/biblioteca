package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.BaseEntity;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;

public abstract class AbstractService  {

	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService ;
	
	protected void checaExistente(BaseEntity obj) throws RegistroNaoEncontradoException{
		if(obj==null){
			throw new RegistroNaoEncontradoException();
		}
	}
	
	protected void checaPermissoes(BaseEntity entidade) throws PermissaoAlteracaoNegadaException{
		if(!alteracaoUsuarioSecurityService.podeAlterar(entidade)){
			throw new PermissaoAlteracaoNegadaException();
		}
	}
	
	protected void regrasBasicas(BaseEntity obj) throws RegistroNaoEncontradoException, PermissaoAlteracaoNegadaException{
		checaExistente(obj);
		checaPermissoes(obj);
	}
}
