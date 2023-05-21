package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Categoria;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Categoria categoria) {
		sessionFactory.getCurrentSession().saveOrUpdate(categoria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> pesquisarCategorias() {
		return sessionFactory.getCurrentSession().createQuery("from Categoria").list();
	}

	@Override
	public void deletar(Long idCategoria) {
		Categoria categoria = (Categoria) sessionFactory.getCurrentSession().load(Categoria.class, idCategoria);
		if (null != categoria) {
			this.sessionFactory.getCurrentSession().delete(categoria);
		}
	}

	@Override
	public Categoria pesquisarCategoriaPorId(Long idCategoria) {
		return (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, idCategoria);
	}

}
