package br.com.viniciusmrosa.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JoinColumn( name = "usuario_id", nullable = false, updatable = false, referencedColumnName = "id")
	@OneToOne(targetEntity = Usuario.class)
	private Usuario usuarioCriacao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Transient
	public LogCriacao getLogCriacao() {
		return new LogCriacao(this.usuarioCriacao, this.dataCriacao);
	}

	@Transient
	public void setLogCriacao(LogCriacao log) {

		this.dataCriacao = log.getDataCriacao();
		this.usuarioCriacao = log.getUsuarioCriacao();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
