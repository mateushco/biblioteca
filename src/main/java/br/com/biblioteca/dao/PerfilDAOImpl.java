package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Perfil;

@Repository
public class PerfilDAOImpl implements PerfilDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Perfil perfil) {
		sessionFactory.getCurrentSession().saveOrUpdate(perfil);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> pesquisarPerfils() {
		return sessionFactory.getCurrentSession().createQuery("from Perfil").list();
	}

	@Override
	public void deletar(Long idPerfil) {
		Perfil perfil = (Perfil) sessionFactory.getCurrentSession().load(Perfil.class, idPerfil);
		if (null != perfil) {
			this.sessionFactory.getCurrentSession().delete(perfil);
		}
	}

	@Override
	public Perfil pesquisarPerfilPorId(Long idPerfil) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Perfil.class);
		criteria.add(Restrictions.eq("id", idPerfil));

		Perfil perfil = (Perfil) criteria.uniqueResult();
		if (perfil != null)
			Hibernate.initialize(perfil.getFuncionalidades());

		return perfil;

	}

}
