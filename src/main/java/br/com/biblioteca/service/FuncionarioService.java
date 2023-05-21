package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.model.Funcionario;

public interface FuncionarioService {

	public void salvar(Funcionario funcionario);

	public List<Funcionario> pesquisarFuncionarios();

	public void deletar(Long idFuncionario);

	public Funcionario pesquisarFuncionarioPorId(Long idFuncionario);

	public Funcionario pesquisarFuncionarioPorLoginSenha(String login, String senha);

	public void alterarSenha(Long idFuncionario, String senha);

	public void atualizarDados(Funcionario funcionario);

	public Funcionario pesquisarFuncionarioPorLogin(String login);

	public Funcionario pesquisarFuncionarioPorEmail(String email);

	public Funcionario pesquisarFuncionarioPorCPF(String cpf);

}