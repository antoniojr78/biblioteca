package br.com.viniciusmrosa.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ParametroSistema extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=20)
	private String codParam;
	
	@Column(length=150)
	private String valorParam;
	
	@Column(length=200)
	private String nomeParam;

	public ParametroSistema() {
	
	}
	public String getCodParam() {
		return codParam;
	}

	public void setCodParam(String codParam) {
		this.codParam = codParam;
	}

	public String getValorParam() {
		return valorParam;
	}

	public void setValorParam(String valorParam) {
		this.valorParam = valorParam;
	}

	public String getNomeParam() {
		return nomeParam;
	}

	public void setNomeParam(String nomeParam) {
		this.nomeParam = nomeParam;
	}
	
	
}
