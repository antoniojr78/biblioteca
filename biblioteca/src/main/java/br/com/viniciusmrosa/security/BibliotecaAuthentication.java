package br.com.viniciusmrosa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.viniciusmrosa.modelo.Usuario;

public class BibliotecaAuthentication implements Authentication {

	private Usuario usuAutenticado;
	private boolean autenticado;
	private List<GrantedAuthority> permissoes =new ArrayList<GrantedAuthority>();
	
	
	public BibliotecaAuthentication(Usuario usuAutenticado) {
		super();
		this.usuAutenticado = usuAutenticado;
		this.permissoes =new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return  usuAutenticado.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		permissoes.clear();
		permissoes.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ADMIN";
			}
		});
		return permissoes;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return usuAutenticado.getSenha();
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return usuAutenticado;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return usuAutenticado.getLogin();
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return this.autenticado;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.autenticado = authenticated;
	}

}
