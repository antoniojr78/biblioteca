package br.com.viniciusmrosa.exception;

public class ErroOperacaoBDException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErroOperacaoBDException(String msg){
		super(msg);
	}
}
