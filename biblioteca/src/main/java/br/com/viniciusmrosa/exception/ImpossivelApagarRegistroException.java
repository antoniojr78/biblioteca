package br.com.viniciusmrosa.exception;

import br.com.viniciusmrosa.utils.SpringUtils;

public class ImpossivelApagarRegistroException extends ErroOperacaoBDException {


	public ImpossivelApagarRegistroException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	public ImpossivelApagarRegistroException() {
		super(SpringUtils.getMessage("impossivel.deletar"));
		//super("Ocorreu um erro ao deletar o registro. O registro possui relação em outra parte do sistema");
		// TODO Auto-generated constructor stub
	}
	
	private static final long serialVersionUID = 1L;

}
