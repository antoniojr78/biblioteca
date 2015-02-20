package br.com.viniciusmrosa.dao.hibernate;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.modelo.Usuario;

public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}

}
