package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Autor;

public interface AutorService {
	
	public void inserir(Autor autor) ;
	public void alterar(Autor autor) throws NegocioException,ErroOperacaoBDException;
	public void excluir(Long id) throws NegocioException,ErroOperacaoBDException;
}
