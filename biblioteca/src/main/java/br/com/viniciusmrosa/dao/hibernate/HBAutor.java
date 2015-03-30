package br.com.viniciusmrosa.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.modelo.Autor;

@Repository("daoAutor")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBAutor extends HBDAO<Autor> implements DAOAutor {

	
	
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Autor.class;
	}

	
}
