package br.com.viniciusmrosa.filtrosrel;

import java.util.Date;

public class FiltroRelEntidadeBase {

	private String parteNome;
	private Date   dataCriacao;
	private String criadoPor;
	
	public String getParteNome() {
		return parteNome;
	}
	public void setParteNome(String parteNome) {
		this.parteNome = parteNome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getCriadoPor() {
		return criadoPor;
	}
	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}
	
	
	
}
