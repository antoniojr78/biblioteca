package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.modelo.Usuario;

public interface UsuarioService  {

	public abstract void inserirUsuario(Usuario u)
			throws RegistroExistenteException;
	
	public void alterarUsuario(Usuario u);
	public void excluiUsuario(Usuario u) throws Exception;
}