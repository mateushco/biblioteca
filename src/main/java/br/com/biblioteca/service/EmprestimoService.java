package br.com.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.biblioteca.model.Emprestimo;

public interface EmprestimoService {

	public void salvar(Emprestimo emprestimo);

	public List<Emprestimo> pesquisarEmprestimos();

	public List<Emprestimo> pesquisarEmprestimosAtivos();

	public void deletar(Long idEmprestimo);

	public Emprestimo pesquisarEmprestimoPorId(Long idEmprestimo);

	public boolean isUsuarioTemEmprestimoAtivo(Long idUsuario);

	public void renovar(Long idEmprestimo);

	public void devolver(Long idEmprestimo);

	public List<Emprestimo> pesquisarEmprestimoPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim);

}