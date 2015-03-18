package br.com.viniciusmrosa.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Colecao extends BaseEntity implements Serializable {


	private static final long serialVersionUID = 1L;


	@Column(nullable=false,length=100)
	private String nome;	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	

}
