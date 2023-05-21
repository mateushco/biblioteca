package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Categoria;

public interface CategoriaDAO {

	public void salvar(Categoria categoria);

	public List<Categoria> pesquisarCategorias();

	public void deletar(Long idCategoria);

	public Categoria pesquisarCategoriaPorId(Long idCategoria);

}
