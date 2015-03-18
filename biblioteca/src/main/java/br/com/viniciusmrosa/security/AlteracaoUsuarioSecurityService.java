package br.com.viniciusmrosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.Usuario;
import br.com.viniciusmrosa.services.UsuarioService;

@Component("AlteracaoUsuarioSecurityService")
public class AlteracaoUsuarioSecurityService {
		
	@Autowired
	SecurityUtils securityUtils;
	
	public boolean podeAlterar(Usuario usuario){
		
		return usuario.equals(securityUtils.buscaUsuarioLogado())
				|| usuario.getUsuarioCriacao().equals(securityUtils.buscaUsuarioLogado()) ;
				
		
	}


}
