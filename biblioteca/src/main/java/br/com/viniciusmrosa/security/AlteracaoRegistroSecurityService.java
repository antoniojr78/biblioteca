package br.com.viniciusmrosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.BaseEntity;
import br.com.viniciusmrosa.modelo.Usuario;

@Component("AlteracaoRegistroSecurityService")
public class AlteracaoRegistroSecurityService {
		
	@Autowired
	SecurityUtils securityUtils;
	
	public boolean podeAlterar(BaseEntity entidade){
		
		return isUsuarioLogado(entidade)
				|| isDonoRegistro(entidade) || isMaster();
				
		
	}
	
	private boolean isUsuarioLogado(BaseEntity entidade){
		if(entidade instanceof Usuario){
			return entidade.equals(securityUtils.buscaUsuarioLogado());
		}
		return false;
	}

	private boolean isDonoRegistro(BaseEntity entidade){
		return entidade.getLogCriacao().getUsuarioCriacao().equals(securityUtils.buscaUsuarioLogado());
		
	}
	
	public boolean isMaster(){
		return securityUtils.buscaUsuarioLogado().isMaster();
	}

}
