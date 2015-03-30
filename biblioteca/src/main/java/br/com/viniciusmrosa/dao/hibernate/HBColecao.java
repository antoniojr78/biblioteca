package br.com.viniciusmrosa.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOColecao;
import br.com.viniciusmrosa.modelo.Colecao;

@Repository("daoColecao")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBColecao extends HBDAO<Colecao> implements DAOColecao {



	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Colecao.class;
	}

}
