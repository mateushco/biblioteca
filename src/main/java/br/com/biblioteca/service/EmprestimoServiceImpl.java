package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Autowired
	private EmprestimoDAO emprestimoDAO;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private LivroDAO livroDAO;

	@Override
	@Transactional
	public void salvar(Emprestimo emprestimo) {

		Cliente cliente = clienteDAO.pesquisarClientePorId(emprestimo.getIdCliente());

		String idsLivros[] = emprestimo.getIdsLivros().split(",");

		List<Livro> livros = new ArrayList<Livro>();
		for (int i = 0; i < idsLivros.length; i++) {
			Livro livro = livroDAO.pesquisarLivroPorId(Long.parseLong(idsLivros[i]));
			livro.setQtdDisponivel(livro.getQtdDisponivel() - 1);
			livros.add(livro);
		}

		emprestimo.setIdentificador("EMP-" + (emprestimoDAO.pesquisarEmprestimos().size() + 1));
		emprestimo.setCliente(cliente);
		emprestimo.setLivros(livros);
		emprestimo.setDataEmprestimo(LocalDateTime.now());
		emprestimo.setDataPrevisaoEntrega(LocalDateTime.now().plusDays(10));

		emprestimoDAO.salvar(emprestimo);
	}

	@Override
	@Transactional
	public void renovar(Long idEmprestimo) {
		Emprestimo emprestimo = emprestimoDAO.pesquisarEmprestimoPorId(idEmprestimo);
		emprestimo.setDataPrevisaoEntrega(emprestimo.getDataPrevisaoEntrega().plusDays(10));
		emprestimoDAO.salvar(emprestimo);
	}

	@Override
	@Transactional
	public void devolver(Long idEmprestimo) {
		Emprestimo emprestimo = emprestimoDAO.pesquisarEmprestimoPorId(idEmprestimo);
		emprestimo.getLivros().forEach(livro -> livro.setQtdDisponivel(livro.getQtdDisponivel() + 1));
		emprestimo.setAtivo(false);
		emprestimo.setDataEntrega(LocalDateTime.now());
		emprestimoDAO.salvar(emprestimo);
	}

	@Override
	@Transactional
	public List<Emprestimo> pesquisarEmprestimos() {
		return emprestimoDAO.pesquisarEmprestimos();
	}

	@Override
	@Transactional
	public void deletar(Long idEmprestimo) {
		emprestimoDAO.deletar(idEmprestimo);
	}

	@Override
	@Transactional
	public Emprestimo pesquisarEmprestimoPorId(Long idEmprestimo) {
		return emprestimoDAO.pesquisarEmprestimoPorId(idEmprestimo);
	}

	@Override
	@Transactional
	public boolean isUsuarioTemEmprestimoAtivo(Long idUsuario) {

		Emprestimo emprestimo = emprestimoDAO.pesquisarEmprestimoAtivoPorCliente(idUsuario);
		if (emprestimo == null)
			return false;
		return true;
	}

	@Override
	@Transactional
	public List<Emprestimo> pesquisarEmprestimosAtivos() {
		return emprestimoDAO.pesquisarEmprestimosAtivos();
	}

	@Override
	@Transactional
	public List<Emprestimo> pesquisarEmprestimoPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim) {
		return emprestimoDAO.pesquisarEmprestimoPorIntervaloDatas(dataInicio, dataFim);
	}

}