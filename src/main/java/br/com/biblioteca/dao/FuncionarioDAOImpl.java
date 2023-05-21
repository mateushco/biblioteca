package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Funcionario;

@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Funcionario funcionario) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(funcionario);
			session.getTransaction().commit();
		} catch (HibernateException hex) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			throw hex;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> pesquisarFuncionarios() {
		return sessionFactory.getCurrentSession().createQuery("from Funcionario").list();

	}

	@Override
	public void deletar(Long idFuncionario) {
		Funcionario funcionario = (Funcionario) sessionFactory.getCurrentSession().load(Funcionario.class,
				idFuncionario);
		if (null != funcionario) {
			this.sessionFactory.getCurrentSession().delete(funcionario);
		}
	}

	@Override
	public Funcionario pesquisarFuncionarioPorId(Long idFuncionario) {
		return (Funcionario) sessionFactory.getCurrentSession().get(Funcionario.class, idFuncionario);
	}

	@Override
	public Funcionario pesquisarFuncionarioPorLoginSenha(String login, String senha) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));

		Funcionario funcionario = (Funcionario) criteria.uniqueResult();
		if (funcionario != null)
			Hibernate.initialize(funcionario.getPerfil().getFuncionalidades());

		return funcionario;
	}
	
	@Override
	public Funcionario pesquisarFuncionarioPorLogin(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("login", login));

		Funcionario funcionario = (Funcionario) criteria.uniqueResult();
		if (funcionario != null)
			Hibernate.initialize(funcionario.getPerfil().getFuncionalidades());

		return funcionario;
	}

	@Override
	public Funcionario pesquisarFuncionarioPorCPF(String cpf) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("cpf", cpf));

		Funcionario funcionario = (Funcionario) criteria.uniqueResult();
		if (funcionario != null)
			Hibernate.initialize(funcionario.getPerfil().getFuncionalidades());

		return funcionario;
	}

	@Override
	public Funcionario pesquisarFuncionarioPorEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("email", email));

		Funcionario funcionario = (Funcionario) criteria.uniqueResult();
		if (funcionario != null)
			Hibernate.initialize(funcionario.getPerfil().getFuncionalidades());

		return funcionario;
	}

}
