package br.com.viniciusmrosa.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOLivro;
import br.com.viniciusmrosa.modelo.Livro;

@Repository(value="daoLivro")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBLivro extends HBDAO<Livro> implements DAOLivro {

	/*@Override
	public List<Livro> lista(int offset, int max) {
		// sobrescrever o método para não trazer todos os relacionementos das entidades
		Query query = getSession().createQuery("select l from Livro l JOIN "
				+ " l.autor JOIN l.colecao JOIN l.editora JOIN l.usuarioCriacao")
				.setFirstResult(offset);
		if(max>0){
			query.setMaxResults(max);
		}
		
		return query.list();
	}
		*/
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return Livro.class;
	}

	
}
