package br.com.viniciusmrosa.exception;

public class ErroOperacaoBDException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public ErroOperacaoBDException(String msg){
		super(msg);
	}
}
