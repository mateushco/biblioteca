package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.PerfilDAO;
import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.utils.SenhaUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;

	@Override
	@Transactional
	public void salvar(Usuario usuario) {
		Perfil perfil = perfilDAO.pesquisarPerfilPorId(usuario.getIdPerfil());
		usuario.setPerfil(perfil);
		usuarioDAO.salvar(usuario);
	}

	@Override
	@Transactional
	public List<Usuario> pesquisarUsuarios() {
		return usuarioDAO.pesquisarUsuarios();
	}

	@Override
	@Transactional
	public void deletar(Long idUsuario) {
		usuarioDAO.deletar(idUsuario);
	}

	@Override
	@Transactional
	public Usuario pesquisarUsuarioPorId(Long idUsuario) {
		return usuarioDAO.pesquisarUsuarioPorId(idUsuario);
	}

	@Override
	@Transactional
	public Usuario pesquisarUsuarioPorLoginSenha(String login, String senha) {
		String senhaMD5 = SenhaUtils.convertStringToMd5(senha);
		return usuarioDAO.pesquisarUsuarioPorLoginSenha(login, senhaMD5);
	}

	@Override
	@Transactional
	public void alterarSenha(Long idUsuario, String senha) {
		Usuario usuario = pesquisarUsuarioPorId(idUsuario);
		usuario.setSenha(SenhaUtils.convertStringToMd5(senha));
		this.usuarioDAO.salvar(usuario);

	}

}