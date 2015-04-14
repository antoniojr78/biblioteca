package br.com.viniciusmrosa.exception;

public class PermissaoAlteracaoNegadaException extends NegocioException{
	
	private static final long serialVersionUID = 1L;


	public PermissaoAlteracaoNegadaException(){
		super("Você nâo tem permissão para alterar/deletar esse registro");
	}
	

	public PermissaoAlteracaoNegadaException(String msg){
		super(msg);
	}
}
