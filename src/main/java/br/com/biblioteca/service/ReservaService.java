package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.biblioteca.model.Reserva;

public interface ReservaService {

	public void salvar(Reserva reserva);

	public List<Reserva> pesquisarReservas();

	public List<Reserva> pesquisarReservasAtivos();

	public void deletar(Long idReserva);

	public Reserva pesquisarReservaPorId(Long idReserva);

	public boolean isUsuarioTemReservaAtivo(Long idUsuario);

	public void cancelar(Long idReserva);

	public void devolver(Long idReserva);

	public void efetivar(Reserva reserva);

	public List<Reserva> pesquisarReservasUsuarioLogado(Long idUsuario);

	public List<Reserva> pesquisarReservaPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim);

}