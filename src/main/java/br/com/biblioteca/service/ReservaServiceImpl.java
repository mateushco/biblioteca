package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.dao.ReservaDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.StatusReserva;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaDAO reservaDAO;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private LivroDAO livroDAO;

	@Override
	@Transactional
	public void salvar(Reserva reserva) {

		Cliente cliente = clienteDAO.pesquisarClientePorId(reserva.getIdCliente());

		String idsLivros[] = reserva.getIdsLivros().split(",");

		List<Livro> livros = new ArrayList<Livro>();
		for (int i = 0; i < idsLivros.length; i++) {
			Livro livro = livroDAO.pesquisarLivroPorId(Long.parseLong(idsLivros[i]));
			livros.add(livro);
		}

		reserva.setIdentificador("RES-" + (reservaDAO.pesquisarReservas().size() + 1));
		reserva.setCliente(cliente);
		reserva.setLivros(livros);
		reserva.setDataReserva(LocalDateTime.now());

		reservaDAO.salvar(reserva);
	}

	@Override
	@Transactional
	public void cancelar(Long idReserva) {
		Reserva reserva = reservaDAO.pesquisarReservaPorId(idReserva);
		reserva.setStatus(StatusReserva.CANCELADA);
		reserva.setDataStatus(LocalDateTime.now());
		reservaDAO.salvar(reserva);
	}

	@Override
	@Transactional
	public void devolver(Long idReserva) {
		Reserva reserva = reservaDAO.pesquisarReservaPorId(idReserva);
		reservaDAO.salvar(reserva);
	}

	@Override
	@Transactional
	public List<Reserva> pesquisarReservas() {
		return reservaDAO.pesquisarReservas();
	}
	
	
	@Override
	@Transactional
	public List<Reserva> pesquisarReservasUsuarioLogado(Long idUsuario) {
		return reservaDAO.pesquisarReservasUsuarioLogado(idUsuario);
	}
	
	@Override
	@Transactional
	public void deletar(Long idReserva) {
		reservaDAO.deletar(idReserva);
	}

	@Override
	@Transactional
	public Reserva pesquisarReservaPorId(Long idReserva) {
		return reservaDAO.pesquisarReservaPorId(idReserva);
	}

	@Override
	@Transactional
	public boolean isUsuarioTemReservaAtivo(Long idUsuario) {

		Reserva reserva = reservaDAO.pesquisarReservaAtivoPorCliente(idUsuario);
		if (reserva == null)
			return false;
		return true;
	}

	@Override
	@Transactional
	public List<Reserva> pesquisarReservasAtivos() {
		return reservaDAO.pesquisarReservasAtivos();
	}

	@Override
	@Transactional
	public void efetivar(Reserva reserva) {
		reserva.setStatus(StatusReserva.EFETIVADA);
		reserva.setDataStatus(LocalDateTime.now());
		reservaDAO.salvar(reserva);
	}

	@Override
	@Transactional
	public List<Reserva> pesquisarReservaPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
		return reservaDAO.pesquisarReservaPorIntervaloDatas(dataInicio, dataFim);
	}

}