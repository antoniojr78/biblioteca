package br.com.viniciusmrosa.modelo;

import java.io.Serializable;
import java.util.Date;

public class LogCriacao implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private Usuario usuarioCriacao ;
	private Date dataCriacao;
	
	public LogCriacao(Usuario usuarioCriacao, Date dataCriacao) {
		this.usuarioCriacao = usuarioCriacao;
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}




	
	
	
}
