package br.com.viniciusmrosa.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.viniciusmrosa.dao.DAOParametros;
import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.modelo.ParametroSistema;
import br.com.viniciusmrosa.modelo.ParametrosCmd;
import br.com.viniciusmrosa.modelo.Usuario;

public class BibliotecaAuthProvider implements AuthenticationProvider{

	@Autowired
	private DAOUsuario daoUsuario;
	
	@Autowired
	private DAOParametros daoParametros;
	
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
		Map<String, String> parametrosGlobais = new HashMap<String, String>();
		
		
		
		ParametrosCmd parametrosCmd = new ParametrosCmd(daoParametros.lista(0, 0));
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		request.getSession().setAttribute("parametrosGlobais", parametrosCmd.getAsMap());
		return resultado;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
	}

}
