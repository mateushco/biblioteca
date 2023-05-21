package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Autor;

@Repository
public class AutorDAOImpl implements AutorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Autor autor) {
		sessionFactory.getCurrentSession().saveOrUpdate(autor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autor> pesquisarAutores() {
		return sessionFactory.getCurrentSession().createQuery("from Autor").list();
	}

	@Override
	public void deletar(Long idAutor) {
		Autor autor = (Autor) sessionFactory.getCurrentSession().load(Autor.class, idAutor);
		if (null != autor) {
			this.sessionFactory.getCurrentSession().delete(autor);
		}
	}

	@Override
	public Autor pesquisarAutorPorId(Long idAutor) {
		return (Autor) sessionFactory.getCurrentSession().get(Autor.class, idAutor);
	}

}
