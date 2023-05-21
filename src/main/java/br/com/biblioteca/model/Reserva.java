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
@Table(name = "reserva")
public class Reserva {

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

	@Column(name = "data_reserva")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataReserva;

	@Column(name = "data_status")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataStatus;

	@Column(name = "status")
	private StatusReserva status;

	@Transient
	private String dataReservaFormatada;

	@Transient
	private String dataStatusFormatada;

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

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public LocalDateTime getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDateTime dataReserva) {
		this.dataReserva = dataReserva;
	}

	public LocalDateTime getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(LocalDateTime dataStatus) {
		this.dataStatus = dataStatus;
	}

	public StatusReserva getStatus() {
		return status;
	}

	public void setStatus(StatusReserva status) {
		this.status = status;
	}

	public String getDataReservaFormatada() {
		return DataUtils.converterLocalDateTimeParaStringDate(dataReserva);
	}

	public void setDataReservaFormatada(String dataReservaFormatada) {
		this.dataReservaFormatada = dataReservaFormatada;
	}

	public String getDataStatusFormatada() {
		return DataUtils.converterLocalDateTimeParaStringDate(dataStatus);
	}

	public void setDataStatusFormatada(String dataStatusFormatada) {
		this.dataStatusFormatada = dataStatusFormatada;
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

}
