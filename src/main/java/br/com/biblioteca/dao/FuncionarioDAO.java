package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Funcionario;

public interface FuncionarioDAO {

	public void salvar(Funcionario funcionario);

	public List<Funcionario> pesquisarFuncionarios();

	public void deletar(Long idFuncionario);

	public Funcionario pesquisarFuncionarioPorId(Long idFuncionario);

	public Funcionario pesquisarFuncionarioPorLoginSenha(String login, String senhaMD5);

	public Funcionario pesquisarFuncionarioPorLogin(String login);

	public Funcionario pesquisarFuncionarioPorEmail(String email);

	public Funcionario pesquisarFuncionarioPorCPF(String cpf);

}
