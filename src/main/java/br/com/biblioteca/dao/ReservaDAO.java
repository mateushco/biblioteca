package br.com.biblioteca.dao;

import java.time.LocalDateTime;
import java.util.List;

import br.com.biblioteca.model.Reserva;

public interface ReservaDAO {

	public void salvar(Reserva reserva);

	public List<Reserva> pesquisarReservas();
	
	public List<Reserva> pesquisarReservasAtivos();

	public void deletar(Long idReserva);

	public Reserva pesquisarReservaPorId(Long idReserva);
	
	public Reserva pesquisarReservaAtivoPorCliente(Long idCliente);

	public List<Reserva> pesquisarReservasUsuarioLogado(Long idUsuario);

	public List<Reserva> pesquisarReservaPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim);

}
