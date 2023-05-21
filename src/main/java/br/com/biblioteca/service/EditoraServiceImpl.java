package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Editora;

@Service
public class EditoraServiceImpl implements EditoraService {

	@Autowired
	private EditoraDAO editoraDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Editora editora) {
		editoraDAO.salvar(editora);
	}

	@Override
	@Transactional
	public List<Editora> pesquisarEditoras() {
		return editoraDAO.pesquisarEditoras();
	}

	@Override
	@Transactional
	public void deletar(Long idEditora) {
		editoraDAO.deletar(idEditora);
	}

	@Override
	@Transactional
	public Editora pesquisarEditoraPorId(Long idEditora) {
		return editoraDAO.pesquisarEditoraPorId(idEditora);
	}

	@Override
	@Transactional
	public String gerarMatricula() {
		String codEditora = "100";
		int qtd = usuarioDAO.pesquisarUsuarios().size();
		return codEditora + (qtd + 1);
	}

}