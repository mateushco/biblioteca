package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Perfil;

public interface PerfilDAO {

	public void salvar(Perfil perfil);

	public List<Perfil> pesquisarPerfils();

	public void deletar(Long idPerfil);

	public Perfil pesquisarPerfilPorId(Long idPerfil);

}
