package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Livro;

@Repository
public class LivroDAOImpl implements LivroDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Livro livro) {
		sessionFactory.getCurrentSession().saveOrUpdate(livro);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> pesquisarLivros() {
		return sessionFactory.getCurrentSession().createQuery("from Livro").list();
	}

	@Override
	public void deletar(Long idLivro) {
		Livro livro = (Livro) sessionFactory.getCurrentSession().load(Livro.class, idLivro);
		if (null != livro) {
			this.sessionFactory.getCurrentSession().delete(livro);
		}
	}

	@Override
	public Livro pesquisarLivroPorId(Long idLivro) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Livro.class);
		criteria.add(Restrictions.eq("id", idLivro));

		Livro livro = (Livro) criteria.uniqueResult();
		if (livro != null)
			Hibernate.initialize(livro.getAutores());

		return livro;

	
	}

	@Override
	public Livro pesquisarLivroPorISBN(String isbn) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Livro.class);
		criteria.add(Restrictions.eq("isbn", isbn));
		return (Livro) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> pesquisarLivrosDisponiveis() {
		return sessionFactory.getCurrentSession().createQuery("from Livro where qtdDisponivel > 0").list();
	}

}
