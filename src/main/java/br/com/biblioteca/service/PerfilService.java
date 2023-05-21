package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.model.Perfil;

public interface PerfilService {

	public void salvar(Perfil perfil);

	public List<Perfil> pesquisarPerfils();

	public void deletar(Long idPerfil);

	public Perfil pesquisarPerfilPorId(Long idPerfil);

}