package br.com.biblioteca.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.biblioteca.utils.DataUtils;
import br.com.biblioteca.utils.LocalDateTimeConverter;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "identificador")
	private String identificador;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;

	@ManyToMany
	private List<Livro> livros = new ArrayList<Livro>();

	@Column(name = "data_emprestimo")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataEmprestimo;

	@Column(name = "data_entrega")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataEntrega;

	@Column(name = "data_previsao_entrega")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataPrevisaoEntrega;

	@Column(name = "ativo")
	private boolean ativo = true;

	// Campos Transient
	@Transient
	private String dataEmprestimoFormatada;

	@Transient
	private String dataEntregaFormatada;

	@Transient
	private String dataPrevisaoEntregaFormatada;

	@Transient
	private String idsLivros;

	@Transient
	private Long idCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getIdsLivros() {
		return idsLivros;
	}

	public void setIdsLivros(String idsLivros) {
		this.idsLivros = idsLivros;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDataEmprestimoFormatada() {
		return DataUtils.converterLocalDateTimeParaStringDateTime(dataEmprestimo);
	}

	public void setDataEmprestimoFormatada(String dataEmprestimoFormatada) {
		this.dataEmprestimoFormatada = dataEmprestimoFormatada;
	}

	public String getDataEntregaFormatada() {
		return DataUtils.converterLocalDateTimeParaStringDate(dataEntrega);
	}

	public void setDataEntregaFormatada(String dataEntregaFormatada) {
		this.dataEntregaFormatada = dataEntregaFormatada;
	}

	public String getDataPrevisaoEntregaFormatada() {
		return DataUtils.converterLocalDateTimeParaStringDate(dataPrevisaoEntrega);
	}

	public void setDataPrevisaoEntregaFormatada(String dataPrevisaoEntregaFormatada) {
		this.dataPrevisaoEntregaFormatada = dataPrevisaoEntregaFormatada;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getStatusStr() {
		if (ativo)
			return "Empréstimo Ativo";
		else
			return "Empréstimo Finalizado";
	}

	public LocalDateTime getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(LocalDateTime dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

}
