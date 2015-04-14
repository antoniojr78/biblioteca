package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Colecao;

public interface ColecaoService {

	public void inserir(Colecao c) ;
	public void alterar(Colecao c) throws NegocioException,ErroOperacaoBDException;
	public void excluir(Long id) throws NegocioException,ErroOperacaoBDException;
}