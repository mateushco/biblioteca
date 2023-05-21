package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.model.Editora;

public interface EditoraService {

	public void salvar(Editora editora);

	public List<Editora> pesquisarEditoras();

	public void deletar(Long idEditora);

	public Editora pesquisarEditoraPorId(Long idEditora);
	
	public String gerarMatricula();
	
}