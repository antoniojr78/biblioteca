package br.com.viniciusmrosa.dao.hibernate;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.viniciusmrosa.dao.DAOBase;
import br.com.viniciusmrosa.modelo.BaseEntity;
import br.com.viniciusmrosa.security.SecurityUtils;
import br.com.viniciusmrosa.services.LogCriacaoService;

/*
 * Essa classe abstrata ser� utilizada para ser extendida pelas classes de implementa��o dos DAOs para o Hibernate
 * 
 * 
 * */
@Transactional(propagation=Propagation.SUPPORTS)
public abstract class HBDAO<T extends BaseEntity> implements DAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LogCriacaoService logCriacaoService;
	
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
		if(!this.isSaved(obj)){
			obj.setLogCriacao(logCriacaoService.getLogCriacao());
		}
		getSession().saveOrUpdate(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> lista(int offset,int max) {
		Query query = 
		 getSession().createQuery("select o from " + getClazz().getName() +  " o JOIN o.usuarioCriacao u" )
		.setFirstResult(offset);
		if(max > 0){
			query.setMaxResults(max);
		}
		/*
		Criteria criteria = getSession().createCriteria(getClazz());
		criteria.setFirstResult(offset);
		if(max > 0){
			criteria.setMaxResults(max);
		}
		*/
		return query.list();
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

	@Override
	public boolean isSaved(T obj) {
		// TODO Auto-generated method stub
		return obj.getId() !=null;
	}
	
	protected abstract Class getClazz()  ;
	
}
