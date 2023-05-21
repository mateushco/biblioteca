package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.PerfilDAO;
import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.utils.SenhaUtils;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PerfilDAO perfilDAO;

	@Override
	@Transactional
	public void salvar(Cliente cliente) {
		Perfil perfil = perfilDAO.pesquisarPerfilPorId(cliente.getIdPerfil());
		cliente.setPerfil(perfil);

		if (cliente.getId() != null) {
			// atualizar
			Cliente clienteBD = pesquisarClientePorId(cliente.getId());
			cliente.setLogin(clienteBD.getLogin());
			cliente.setSenha(clienteBD.getSenha());
			this.clienteDAO.salvar(cliente);
		} else {
			// inserir
			String senhaMD5 = SenhaUtils.convertStringToMd5(cliente.getSenha());
			cliente.setSenha(senhaMD5);
			this.clienteDAO.salvar(cliente);
		}
	}

	@Override
	@Transactional
	public void salvarExterno(Cliente cliente) {
		Perfil perfil = perfilDAO.pesquisarPerfilPorId(Cliente.ID_PERFIL);
		cliente.setPerfil(perfil);

		// inserir
		if(cliente.getSenha() != null && !cliente.getSenha().isEmpty()) {
			String senhaMD5 = SenhaUtils.convertStringToMd5(cliente.getSenha());
			cliente.setSenha(senhaMD5);
		}

		this.clienteDAO.salvar(cliente);
	}

	@Override
	@Transactional
	public void salvarContinuarCadastro(Cliente cliente) {
		Cliente clienteBD = clienteDAO.pesquisarClientePorId(cliente.getId());
		clienteBD.setTelefone(cliente.getTelefone());
		clienteBD.setCpf(cliente.getCpf());
		this.clienteDAO.salvar(clienteBD);
	}

	@Override
	@Transactional
	public void atualizarDados(Cliente cliente) {

		Cliente clienteBD = pesquisarClientePorId(cliente.getId());

		cliente.setLogin(clienteBD.getLogin());
		cliente.setSenha(clienteBD.getSenha());
		cliente.setPerfil(clienteBD.getPerfil());

		this.clienteDAO.salvar(cliente);

	}

	@Override
	@Transactional
	public List<Cliente> pesquisarClientes() {
		return clienteDAO.pesquisarClientes();
	}

	@Override
	@Transactional
	public void deletar(Long idCliente) {
		clienteDAO.deletar(idCliente);
	}

	@Override
	@Transactional
	public Cliente pesquisarClientePorId(Long idCliente) {
		return clienteDAO.pesquisarClientePorId(idCliente);
	}

	@Override
	@Transactional
	public Cliente pesquisarClientePorLoginSenha(String login, String senha) {
		String senhaMD5 = SenhaUtils.convertStringToMd5(senha);
		return clienteDAO.pesquisarClientePorLoginSenha(login, senhaMD5);
	}

	@Override
	@Transactional
	public Cliente pesquisarClientePorCPF(String cpf) {
		return clienteDAO.pesquisarClientePorCPF(cpf);
	}

	
	@Override
	@Transactional
	public Cliente pesquisarClientePorLogin(String login) {
		return clienteDAO.pesquisarClientePorLogin(login);
	}
	
	@Override
	@Transactional
	public Cliente pesquisarClientePorEmail(String email) {
		return clienteDAO.pesquisarClientePorEmail(email);
	}
	
	@Override
	@Transactional
	public void alterarSenha(Long idCliente, String senha) {
		Cliente cliente = pesquisarClientePorId(idCliente);
		cliente.setSenha(SenhaUtils.convertStringToMd5(senha));
		this.clienteDAO.salvar(cliente);

	}

}