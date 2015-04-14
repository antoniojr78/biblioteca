package br.com.viniciusmrosa.services;

import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.Usuario;

public interface UsuarioService  {

	public abstract void inserirUsuario(Usuario u)
			throws RegistroExistenteException;
	
	public void alterarUsuario(Usuario u) throws NegocioException,ErroOperacaoBDException;
	public void excluiUsuario(Long id) throws NegocioException,ErroOperacaoBDException;
}