package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.model.Cliente;

public interface ClienteService {

	public void salvar(Cliente cliente);

	public List<Cliente> pesquisarClientes();

	public void deletar(Long idCliente);

	public Cliente pesquisarClientePorId(Long idCliente);

	public Cliente pesquisarClientePorLoginSenha(String login, String senha);
	
	public void alterarSenha(Long idCliente, String senha);

	public void atualizarDados(Cliente cliente);

	public void salvarExterno(Cliente cliente);

	public Cliente pesquisarClientePorLogin(String login);
	
	public Cliente pesquisarClientePorEmail(String email);
	
	public void salvarContinuarCadastro(Cliente cliente);

	public Cliente pesquisarClientePorCPF(String cpf);
	
}