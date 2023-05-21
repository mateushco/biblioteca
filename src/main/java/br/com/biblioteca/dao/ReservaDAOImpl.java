package br.com.biblioteca.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.StatusReserva;

@Repository
public class ReservaDAOImpl implements ReservaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Reserva reserva) {
		sessionFactory.getCurrentSession().saveOrUpdate(reserva);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> pesquisarReservas() {
		return sessionFactory.getCurrentSession().createQuery("from Reserva").list();
	}

	@Override
	public void deletar(Long idReserva) {
		Reserva reserva = (Reserva) sessionFactory.getCurrentSession().load(Reserva.class, idReserva);
		if (null != reserva) {
			this.sessionFactory.getCurrentSession().delete(reserva);
		}
	}

	@Override
	public Reserva pesquisarReservaPorId(Long idReserva) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("id", idReserva));

		Reserva reserva = (Reserva) criteria.uniqueResult();
		if (reserva != null)
			Hibernate.initialize(reserva.getLivros());

		return reserva;
	}

	@Override
	public Reserva pesquisarReservaAtivoPorCliente(Long idCliente) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("cliente.id", idCliente));
		criteria.add(Restrictions.eq("status", StatusReserva.ATIVO));

		Reserva reserva = (Reserva) criteria.uniqueResult();
		if (reserva != null)
			Hibernate.initialize(reserva.getLivros());

		return reserva;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> pesquisarReservasAtivos() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("status", StatusReserva.ATIVO));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> pesquisarReservasUsuarioLogado(Long idUsuario) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("cliente.id", idUsuario));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> pesquisarReservaPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reserva.class);
		criteria.add(Restrictions.ge("dataReserva", dataInicio));
		criteria.add(Restrictions.lt("dataReserva", dataFim));
		return criteria.list();
	}

}
