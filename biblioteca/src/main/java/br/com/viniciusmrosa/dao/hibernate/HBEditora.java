package br.com.viniciusmrosa.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOEditora;
import br.com.viniciusmrosa.modelo.Editora;

@Repository("daoEditora")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBEditora extends HBDAO<Editora> implements DAOEditora {


	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Editora.class;
	}

}
