package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Editora;

public interface EditoraDAO {

	public void salvar(Editora editora);

	public List<Editora> pesquisarEditoras();

	public void deletar(Long idEditora);

	public Editora pesquisarEditoraPorId(Long idEditora);

}
