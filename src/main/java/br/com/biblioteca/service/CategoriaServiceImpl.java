package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.CategoriaDAO;
import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Categoria categoria) {
		categoriaDAO.salvar(categoria);
	}

	@Override
	@Transactional
	public List<Categoria> pesquisarCategorias() {
		return categoriaDAO.pesquisarCategorias();
	}

	@Override
	@Transactional
	public void deletar(Long idCategoria) {
		categoriaDAO.deletar(idCategoria);
	}

	@Override
	@Transactional
	public Categoria pesquisarCategoriaPorId(Long idCategoria) {
		return categoriaDAO.pesquisarCategoriaPorId(idCategoria);
	}

	@Override
	@Transactional
	public String gerarMatricula() {
		String codCategoria = "100";
		int qtd = usuarioDAO.pesquisarUsuarios().size();
		return codCategoria + (qtd + 1);
	}

}