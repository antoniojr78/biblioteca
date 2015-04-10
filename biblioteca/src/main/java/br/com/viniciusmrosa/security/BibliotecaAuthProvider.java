package br.com.viniciusmrosa.security;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.modelo.Usuario;

public class BibliotecaAuthProvider implements AuthenticationProvider{

	@Autowired
	private DAOUsuario daoUsuario;
	
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		String username = token.getName();
		String senha    = token.getCredentials() != null ? token.getCredentials().toString() : null;
		
		System.out.println("BibliotecaAuthProvider:HasSenha=" + DigestUtils.sha512Hex(senha));
		
		Usuario usuario = daoUsuario.efetuarLogin(username, DigestUtils.sha512Hex(senha));
		
		if (usuario == null) {
			return null;
		}
		
		BibliotecaAuthentication resultado = new BibliotecaAuthentication(usuario);
		resultado.setAuthenticated(usuario != null);
		return resultado;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
	}

}
