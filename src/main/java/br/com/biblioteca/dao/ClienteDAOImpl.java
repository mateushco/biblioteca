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

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Cliente cliente) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(cliente);
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
	public List<Cliente> pesquisarClientes() {
		return sessionFactory.getCurrentSession().createQuery("from Cliente").list();

	}

	@Override
	public void deletar(Long idCliente) {
		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().load(Cliente.class,
				idCliente);
		if (null != cliente) {
			this.sessionFactory.getCurrentSession().delete(cliente);
		}
	}

	@Override
	public Cliente pesquisarClientePorId(Long idCliente) {
		return (Cliente) sessionFactory.getCurrentSession().get(Cliente.class, idCliente);
	}

	@Override
	public Cliente pesquisarClientePorLoginSenha(String login, String senha) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));

		Cliente cliente = (Cliente) criteria.uniqueResult();
		if (cliente != null)
			Hibernate.initialize(cliente.getPerfil().getFuncionalidades());

		return cliente;
	}
	
	@Override
	public Cliente pesquisarClientePorLogin(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("login", login));

		Cliente cliente = (Cliente) criteria.uniqueResult();
		if (cliente != null)
			Hibernate.initialize(cliente.getPerfil().getFuncionalidades());

		return cliente;
	}

	@Override
	public Cliente pesquisarClientePorCPF(String cpf) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("cpf", cpf));

		Cliente cliente = (Cliente) criteria.uniqueResult();
		if (cliente != null)
			Hibernate.initialize(cliente.getPerfil().getFuncionalidades());

		return cliente;
	}

	@Override
	public Cliente pesquisarClientePorEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("email", email));

		Cliente cliente = (Cliente) criteria.uniqueResult();
		if (cliente != null)
			Hibernate.initialize(cliente.getPerfil().getFuncionalidades());

		return cliente;
	}

}
