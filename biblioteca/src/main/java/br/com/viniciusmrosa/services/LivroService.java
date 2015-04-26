package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Livro;

public interface LivroService {
	public void inserir(Livro livro) ;
	public void excluir(Long id) throws NegocioException,ErroOperacaoBDException;
	public void alteracaoFormLivro(Livro livro) throws NegocioException,ErroOperacaoBDException;
}
