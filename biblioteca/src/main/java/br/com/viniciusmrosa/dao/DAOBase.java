package br.com.viniciusmrosa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;

/**
 * 
 * @author Vinicius
 *
 * @param <T>
 * Interface será usada para ser extendida pelas interfaces DAO requeridas peloSpring.
 * Desse modo evitamos de ter que escrever todos os métodos nas interfaces de cada entidade. 
 */
public interface DAOBase<T> {

	public void salva(T obj);
	public List<T> lista(int offset,int max);
	public T getById(Long id);
	public void deleta(T obj) throws ErroOperacaoBDException;
	public boolean isSaved(T obj);
}
