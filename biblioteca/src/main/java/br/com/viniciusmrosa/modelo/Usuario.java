package br.com.viniciusmrosa.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario extends BaseEntity implements Serializable {


	private static final long serialVersionUID = 1L;


	@Column(length=100,nullable=false)
	@NotEmpty(message="{usuario.nome.vazio}")
	private String nome;
	
	@Column(length=150,nullable=false)
	@NotEmpty(message="{usuario.senha.vazio}")
	private String senha;
	
	@Column(name="senha",updatable=false,insertable=false)
	private String senhaHash;
	
	@Column(length=100,nullable=false)
	@NotEmpty(message="{usuario.login.vazio}") 
	@Email
	private String login;
	

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenhaHash() {
		return senhaHash;
	}
	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}
		
		
}
