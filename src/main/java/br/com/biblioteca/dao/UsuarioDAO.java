package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Usuario;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);

	public List<Usuario> pesquisarUsuarios();

	public void deletar(Long idUsuario);

	public Usuario pesquisarUsuarioPorId(Long idUsuario);

	public Usuario pesquisarUsuarioPorLoginSenha(String login, String senhaMD5);
	
	public Usuario pesquisarUsuarioPorLoginGoogle(String login);

}
