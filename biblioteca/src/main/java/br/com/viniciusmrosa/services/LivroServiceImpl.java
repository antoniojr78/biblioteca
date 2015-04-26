package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOLivro;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.Livro;

@Service(value="livroService")
public class LivroServiceImpl extends AbstractService implements LivroService {

	@Autowired
	private DAOLivro daoLivro;
	
	private Livro livroAlterar;
	
	@Override
	public void inserir(Livro livro) {

		daoLivro.salva(livro);
		
	}

	private Livro verificaRegras(Livro livroget)throws NegocioException, RegistroNaoEncontradoException {
		livroAlterar = daoLivro.getById(livroget.getId());
		regrasBasicas(livroAlterar);
		return livroAlterar;		
	}
	
	public void alteracaoFormLivro(Livro livro) throws NegocioException, ErroOperacaoBDException{
		livroAlterar = verificaRegras(livro);		
		
		//Alterar os campos passiveis de alteracao no form de livro
		livroAlterar.setAutor(livro.getAutor());
		livroAlterar.setColecao(livro.getColecao());
		livroAlterar.setEditora(livro.getEditora());
		livroAlterar.setEmprestado(livro.isEmprestado());
		if(livro.getFoto()!= null){
			livroAlterar.setFoto(livro.getFoto());
		}
		livroAlterar.setIsbn(livro.getIsbn());
		livroAlterar.setListaDesejo(livro.isListaDesejo());
		livroAlterar.setSinopse(livro.getSinopse());
		livroAlterar.setTitulo(livro.getTitulo());
		
		daoLivro.salva(livroAlterar);
		
	}
	@Override
	public void excluir(Long id) throws NegocioException,
			ErroOperacaoBDException {
		
		
	}

}
