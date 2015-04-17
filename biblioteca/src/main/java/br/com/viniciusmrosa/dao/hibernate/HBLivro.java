package br.com.viniciusmrosa.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOLivro;
import br.com.viniciusmrosa.modelo.Livro;

@Repository(value="daoLivro")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBLivro extends HBDAO<Livro> implements DAOLivro {

	
		
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Livro.class;
	}

	
}
