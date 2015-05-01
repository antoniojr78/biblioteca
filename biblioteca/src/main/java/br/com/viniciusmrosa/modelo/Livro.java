package br.com.viniciusmrosa.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.viniciusmrosa.validators.ISBN;

@Entity
public class Livro extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ISBN(message="{livro.isbn.invalido}")
	//@NotEmpty(message="{livro.isbn.vazio}")
	@Column(length=13)
	private String isbn;
	
	@NotEmpty(message="{livro.titulo.vazio}")
	@Column(length=100,nullable=false)
	private String titulo;
	@Lob
	private String sinopse;
	@NotNull(message="{livro.autor.vazio}")
	@ManyToOne(optional=false)
	private Autor autor;
	
	@NotNull(message="{livro.editora.vazio}")
	@ManyToOne(optional=false)
	private Editora editora;
	@ManyToOne(optional=true)
	
	private Colecao colecao;
	@Lob
	private byte[] foto;
	@Column
	private boolean emprestado;
	@Column
	private boolean listaDesejo;
	@Column
	private boolean ativo;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public Colecao getColecao() {
		return colecao;
	}
	public void setColecao(Colecao colecao) {
		this.colecao = colecao;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public boolean isEmprestado() {
		return emprestado;
	}
	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}
	
	public boolean isListaDesejo() {
		return listaDesejo;
	}
	public void setListaDesejo(boolean listaDesejo) {
		this.listaDesejo = listaDesejo;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	


	
	
	
	
	

}
