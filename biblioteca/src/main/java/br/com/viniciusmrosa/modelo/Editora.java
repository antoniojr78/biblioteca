package br.com.viniciusmrosa.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Editora extends BaseEntity implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="{entidades.nome.vazio}")
	@Column(length=100,nullable=false)
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
