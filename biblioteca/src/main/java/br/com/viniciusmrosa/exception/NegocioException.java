package br.com.viniciusmrosa.exception;

public class NegocioException extends SistemaException {

	private static final long serialVersionUID = 1L;

	
	public NegocioException(String msg){
		super(msg);
	}
}
