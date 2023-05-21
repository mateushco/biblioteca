package br.com.biblioteca.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Emprestimo;

@Repository
public class EmprestimoDAOImpl implements EmprestimoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Emprestimo emprestimo) {
		sessionFactory.getCurrentSession().saveOrUpdate(emprestimo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emprestimo> pesquisarEmprestimos() {
		return sessionFactory.getCurrentSession().createQuery("from Emprestimo").list();
	}

	@Override
	public void deletar(Long idEmprestimo) {
		Emprestimo emprestimo = (Emprestimo) sessionFactory.getCurrentSession().load(Emprestimo.class, idEmprestimo);
		if (null != emprestimo) {
			this.sessionFactory.getCurrentSession().delete(emprestimo);
		}
	}

	@Override
	public Emprestimo pesquisarEmprestimoPorId(Long idEmprestimo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Emprestimo.class);
		criteria.add(Restrictions.eq("id", idEmprestimo));

		Emprestimo emprestimo = (Emprestimo) criteria.uniqueResult();
		if (emprestimo != null)
			Hibernate.initialize(emprestimo.getLivros());

		return emprestimo;
	}

	@Override
	public Emprestimo pesquisarEmprestimoAtivoPorCliente(Long idCliente) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Emprestimo.class);
		criteria.add(Restrictions.eq("cliente.id", idCliente));
		criteria.add(Restrictions.eq("ativo", Boolean.TRUE));

		Emprestimo emprestimo = (Emprestimo) criteria.uniqueResult();
		if (emprestimo != null)
			Hibernate.initialize(emprestimo.getLivros());

		return emprestimo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emprestimo> pesquisarEmprestimosAtivos() {
		return sessionFactory.getCurrentSession().createQuery("from Emprestimo where ativo = true").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emprestimo> pesquisarEmprestimoPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Emprestimo.class);
		criteria.add(Restrictions.ge("dataEmprestimo", dataInicio));
		criteria.add(Restrictions.lt("dataEmprestimo", dataFim));

		List<Emprestimo> emprestimos = criteria.list();
		return emprestimos;
	}

}
