package br.com.viniciusmrosa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.Usuario;

@Component
public class SecurityUtils {

		public Usuario buscaUsuarioLogado(){
			SecurityContext context = SecurityContextHolder.getContext();
			
			Authentication auth = context.getAuthentication();
			
			Usuario u= (Usuario) auth.getDetails();
			
			return u;
		}
}
