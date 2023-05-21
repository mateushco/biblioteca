package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Livro;

public interface LivroDAO {

	public void salvar(Livro livro);

	public List<Livro> pesquisarLivros();

	public void deletar(Long idLivro);

	public Livro pesquisarLivroPorId(Long idLivro);
	
	public Livro pesquisarLivroPorISBN(String isbn);
	
	public List<Livro> pesquisarLivrosDisponiveis();

}
