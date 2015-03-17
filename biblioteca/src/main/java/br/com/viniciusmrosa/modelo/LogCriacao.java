package br.com.viniciusmrosa.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.JoinColumnOrFormula;

@Embeddable
public class LogCriacao {
	
	@NotNull
	@JoinColumn(name="usuario_id", insertable=false,updatable=false,referencedColumnName="id")
	@OneToOne(targetEntity=Usuario.class)
	private Usuario usuarioCriacao ;
	
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
	
	
}
