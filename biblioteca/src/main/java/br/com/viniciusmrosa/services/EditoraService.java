package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Editora;

public interface EditoraService {
	
	public void inserir(Editora editora);
	public void alterar(Editora editora) throws NegocioException,ErroOperacaoBDException ;
	public void excluir(Long id) throws NegocioException,ErroOperacaoBDException ;
}
