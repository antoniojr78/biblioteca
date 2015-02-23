package br.com.viniciusmrosa.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.modelo.Usuario;

@Repository("daoUsuario")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Usuario.class;
	}

	@Override
	public Usuario buscaPorLogin(String login) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from Usuario usr where usr.login = :login");
		query.setString("login", login);
		return (Usuario) query.uniqueResult();
		
	}

	@Override
	public Usuario efetuarLogin(String login, String senha) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from Usuario usr where usr.login = :login and senha = :senha");
		query.setString("login", login);
		query.setString("senha", senha);
		return (Usuario) query.uniqueResult();
	}

}
