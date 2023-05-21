package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Funcionalidade;

@Repository
public class FuncionalidadeDAOImpl implements FuncionalidadeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Funcionalidade funcionalidade) {
		sessionFactory.getCurrentSession().saveOrUpdate(funcionalidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionalidade> pesquisarFuncionalidades() {
		return sessionFactory.getCurrentSession().createQuery("from Funcionalidade").list();
	}

	@Override
	public void deletar(Long idFuncionalidade) {
		Funcionalidade funcionalidade = (Funcionalidade) sessionFactory.getCurrentSession().load(Funcionalidade.class,
				idFuncionalidade);
		if (null != funcionalidade) {
			this.sessionFactory.getCurrentSession().delete(funcionalidade);
		}
	}

	@Override
	public Funcionalidade pesquisarFuncionalidadePorId(Long idFuncionalidade) {
		return (Funcionalidade) sessionFactory.getCurrentSession().get(Funcionalidade.class, idFuncionalidade);
	}

}
