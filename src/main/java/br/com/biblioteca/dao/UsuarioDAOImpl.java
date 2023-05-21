package br.com.biblioteca.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.model.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void salvar(Usuario usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> pesquisarUsuarios() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		return criteria.list();
	}

	@Override
	public void deletar(Long idUsuario) {
		Usuario usuario = (Usuario) sessionFactory.getCurrentSession().load(Usuario.class, idUsuario);
		if (null != usuario) {
			this.sessionFactory.getCurrentSession().delete(usuario);
		}
	}

	@Override
	public Usuario pesquisarUsuarioPorId(Long idUsuario) {
		return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
	}

	@Override
	public Usuario pesquisarUsuarioPorLoginSenha(String login, String senha) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		if (usuario != null) 
			Hibernate.initialize(usuario.getPerfil().getFuncionalidades());
		
		return usuario;
	}

	@Override
	public Usuario pesquisarUsuarioPorLoginGoogle(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		if (usuario != null)
			Hibernate.initialize(usuario.getPerfil().getFuncionalidades());

		return usuario;
	}

}
