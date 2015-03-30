package br.com.viniciusmrosa.dao.hibernate;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOBase;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.security.SecurityUtils;

/*
 * Essa classe abstrata será utilizada para ser extendida pelas classes de implementação dos DAOs para o Hibernate
 * 
 * 
 * */
@Transactional(propagation=Propagation.SUPPORTS)
public abstract class HBDAO<T> implements DAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SecurityUtils securityUtils ;
	
	public SessionFactory getSessionFactory() {		
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public void salva(T obj) {
		
		getSession().saveOrUpdate(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> lista(int offset,int max) {
		
		Criteria criteria = getSession().createCriteria(getClazz());
		criteria.setFirstResult(offset);
		if(max > 0){
			criteria.setMaxResults(max);
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		
		return (T) getSession().get(getClazz(),id); 
	}

	@Override
	public void deleta(T obj) throws PersistenceException {


		getSession().delete(obj);


	}

	protected abstract Class getClazz()  ;
	
}
