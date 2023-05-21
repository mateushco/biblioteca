package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Editora;

@Repository
public class EditoraDAOImpl implements EditoraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Editora editora) {
		sessionFactory.getCurrentSession().saveOrUpdate(editora);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Editora> pesquisarEditoras() {
		return sessionFactory.getCurrentSession().createQuery("from Editora").list();
	}

	@Override
	public void deletar(Long idEditora) {
		Editora editora = (Editora) sessionFactory.getCurrentSession().load(Editora.class, idEditora);
		if (null != editora) {
			this.sessionFactory.getCurrentSession().delete(editora);
		}
	}

	@Override
	public Editora pesquisarEditoraPorId(Long idEditora) {
		return (Editora) sessionFactory.getCurrentSession().get(Editora.class, idEditora);
	}

}
