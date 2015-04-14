package br.com.viniciusmrosa.exception;

public class RegistroNaoEncontradoException extends ErroOperacaoBDException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontradoException(){
		super("Registro não encontrado");
	}
	public RegistroNaoEncontradoException(String msg){
		super(msg);
	}

}
