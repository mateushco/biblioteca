package br.com.biblioteca.dao;

import java.time.LocalDateTime;
import java.util.List;

import br.com.biblioteca.model.Emprestimo;

public interface EmprestimoDAO {

	public void salvar(Emprestimo emprestimo);

	public List<Emprestimo> pesquisarEmprestimos();
	
	public List<Emprestimo> pesquisarEmprestimosAtivos();

	public void deletar(Long idEmprestimo);

	public Emprestimo pesquisarEmprestimoPorId(Long idEmprestimo);
	
	public Emprestimo pesquisarEmprestimoAtivoPorCliente(Long idCliente);
	
	public List<Emprestimo> pesquisarEmprestimoPorIntervaloDatas(LocalDateTime dataInicio, LocalDateTime dataFim);

}
