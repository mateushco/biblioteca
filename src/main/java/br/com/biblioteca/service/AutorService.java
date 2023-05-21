package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.model.Autor;

public interface AutorService {

	public void salvar(Autor autor);

	public List<Autor> pesquisarAutores();

	public void deletar(Long idAutor);

	public Autor pesquisarAutorPorId(Long idAutor);
	
	public String gerarMatricula();
	
}