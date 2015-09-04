package br.com.viniciusmrosa.exception;

public class ErroRelatorioPDFException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroRelatorioPDFException() {
		super("Erro ao gerar relatório");
	}

	public ErroRelatorioPDFException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
