package br.com.biblioteca.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "titulo")
	private String titulo;

	@ManyToMany
	@JoinTable(name = "autor_livro", joinColumns = @JoinColumn(name = "id_autor"), inverseJoinColumns = @JoinColumn(name = "id_livro"))
	private List<Autor> autores;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "local_edicao")
	private String localEdicao;

	@ManyToOne
	@JoinColumn(name = "id_editora", referencedColumnName = "id")
	private Editora editora;

	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id")
	private Categoria categoria;

	@Column(name = "ano_edicao")
	private int anoEdicao;

	@Column(name = "numero_paginas")
	private int numeroPaginas;

	@Column(name = "qtd_exemplares")
	private int qtdExemplares;

	@Column(name = "qtd_disponivel")
	private int qtdDisponivel;

	@Transient
	private Long idEditora;

	@Transient
	private Long idCategoria;

	@Transient
	private String idsAutores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLocalEdicao() {
		return localEdicao;
	}

	public void setLocalEdicao(String localEdicao) {
		this.localEdicao = localEdicao;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public int getQtdExemplares() {
		return qtdExemplares;
	}

	public void setQtdExemplares(int qtdExemplares) {
		this.qtdExemplares = qtdExemplares;
	}

	public int getQtdDisponivel() {
		return qtdDisponivel;
	}

	public void setQtdDisponivel(int qtdDisponivel) {
		this.qtdDisponivel = qtdDisponivel;
	}

	public Long getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(Long idEditora) {
		this.idEditora = idEditora;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdsAutores() {
		return idsAutores;
	}

	public void setIdsAutores(String idsAutores) {
		this.idsAutores = idsAutores;
	}

}
