package br.com.viniciusmrosa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOBase;

/*
 * Essa classe abstrata será utilizada para ser extendida pelas classes de implementação dos DAOs para o Hibernate
 * 
 * 
 * */
@Transactional(propagation=Propagation.SUPPORTS)
public abstract class HBDAO<T> implements DAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	
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
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> lista(int offset,int max) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(getClazz()).setFirstResult(offset).setMaxResults(max).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(long id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(getClazz(), id);
	}

	@Override
	public void deleta(T obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	protected abstract Class getClazz()  ;
	
}
