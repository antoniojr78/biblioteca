package br.com.viniciusmrosa.dao;

import br.com.viniciusmrosa.modelo.Usuario;

public interface DAOUsuario extends DAOBase<Usuario> {

	public Usuario buscaPorLogin(String login);
}
