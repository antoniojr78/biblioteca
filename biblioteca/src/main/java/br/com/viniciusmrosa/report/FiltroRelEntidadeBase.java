package br.com.viniciusmrosa.report;

import java.util.Date;

public class FiltroRelEntidadeBase {

	private String parteNome;
	private Date   dataCriacao;
	private String criadoPor;
	private FormatoExport formato = FormatoExport.PDF ;
	
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
	public FormatoExport getFormato() {
		return formato;
	}
	public void setFormato(FormatoExport formato) {
		this.formato = formato;
	}
	
	public FormatoExport[] getFormatos(){
		return FormatoExport.values();
	}
	
}
