package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Autor;

public interface AutorDAO {

	public void salvar(Autor autor);

	public List<Autor> pesquisarAutores();

	public void deletar(Long idAutor);

	public Autor pesquisarAutorPorId(Long idAutor);

}
