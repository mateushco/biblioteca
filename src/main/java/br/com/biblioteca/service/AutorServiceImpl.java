package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.AutorDAO;
import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Autor;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorDAO autorDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Autor autor) {
		autorDAO.salvar(autor);
	}

	@Override
	@Transactional
	public List<Autor> pesquisarAutores() {
		return autorDAO.pesquisarAutores();
	}

	@Override
	@Transactional
	public void deletar(Long idAutor) {
		autorDAO.deletar(idAutor);
	}

	@Override
	@Transactional
	public Autor pesquisarAutorPorId(Long idAutor) {
		return autorDAO.pesquisarAutorPorId(idAutor);
	}

	@Override
	@Transactional
	public String gerarMatricula() {
		String codAutor = "100";
		int qtd = usuarioDAO.pesquisarUsuarios().size();
		return codAutor + (qtd + 1);
	}

}