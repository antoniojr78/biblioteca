package br.com.viniciusmrosa.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOParametros;
import br.com.viniciusmrosa.modelo.Autor;
import br.com.viniciusmrosa.modelo.ParametroSistema;

@Repository("daoParametros")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBParametros extends HBDAO<ParametroSistema> implements DAOParametros {

	
	
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return ParametroSistema.class;
	}

	
}
