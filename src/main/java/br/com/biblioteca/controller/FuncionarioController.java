package br.com.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.service.FuncionarioService;
import br.com.biblioteca.service.PerfilService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private PerfilService perfilService;

	public FuncionarioController() {
		System.out.println("FuncionarioController()");
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(ModelAndView model) throws IOException {
		List<Funcionario> listFuncionario = funcionarioService.pesquisarFuncionarios();
		model.addObject("listFuncionario", listFuncionario);
		model.setViewName("funcionario-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {

		List<Perfil> perfis = perfilService.pesquisarPerfils();

		Funcionario funcionario = new Funcionario();
		model.addObject("perfis", perfis);
		model.addObject("funcionario", funcionario);
		model.setViewName("funcionario-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Funcionario funcionario) {

		ModelAndView model = new ModelAndView();

		List<String> erros = new ArrayList<String>();
		boolean erro = false;
		if (isCpfCadastrado(funcionario)) {
			erro = true;
			erros.add("Já existe um funcionario cadastrado para o CPF informado.");
		}
		if (isLoginCadastrado(funcionario)) {
			erro = true;
			erros.add("Já existe um funcionario cadastrado para o login informado.");
		}
		if (isEmailCadastrado(funcionario)) {
			erro = true;
			erros.add("Já existe um funcionario cadastrado para o e-mail informado.");
		}

		if (erro) {
			List<Perfil> perfis = perfilService.pesquisarPerfils();
			funcionario.getPerfil().setId(funcionario.getIdPerfil());

			model.addObject("funcionario", funcionario);
			model.addObject("perfis", perfis);
			model.addObject("erro", erro);
			model.addObject("erros", erros);
			model.setViewName("funcionario-form");

		} else {
			funcionarioService.salvar(funcionario);
			List<Funcionario> listFuncionario = funcionarioService.pesquisarFuncionarios();

			model.addObject("listFuncionario", listFuncionario);
			model.addObject("sucesso", Boolean.TRUE);
			model.setViewName("funcionario-lista");
		}

		return model;
	}
	
	private boolean isCpfCadastrado(Funcionario funcionario) {

		Funcionario funcionarioBD = funcionarioService.pesquisarFuncionarioPorCPF(funcionario.getCpf());

		if (funcionarioBD != null) {
			// cpf já cadastrado

			if (funcionario.getId() != null) {
				// edição
				if (funcionarioBD.getCpf().equals(funcionario.getCpf())) {
					if (funcionarioBD.getId() == funcionario.getId())
						return false;
					else
						return true;
				}
			}

			return true;
		} else {

			// cpf não cadastrado
			return false;
		}

	}

	private boolean isEmailCadastrado(Funcionario funcionario) {

		Funcionario funcionarioBD = funcionarioService.pesquisarFuncionarioPorEmail(funcionario.getEmail());

		if (funcionarioBD != null) {
			// email já cadastrado

			if (funcionario.getId() != null) {
				// edição
				if (funcionarioBD.getEmail().equals(funcionario.getEmail())) {
					if (funcionarioBD.getId() == funcionario.getId())
						return false;
					else
						return true;
				}
			}

			return true;
		} else {

			// email não cadastrado
			return false;
		}

	}

	private boolean isLoginCadastrado(Funcionario funcionario) {

		Funcionario funcionarioBD = funcionarioService.pesquisarFuncionarioPorLogin(funcionario.getLogin());

		if (funcionarioBD != null) {
			// login já cadastrado

			if (funcionario.getId() != null) {
				// edição
				if (funcionarioBD.getLogin().equals(funcionario.getLogin())) {
					if (funcionarioBD.getId() == funcionario.getId())
						return false;
					else
						return true;
				}
			}

			return true;
		} else {

			// login não cadastrado
			return false;
		}

	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		funcionarioService.deletar(id);

		List<Funcionario> listFuncionario = funcionarioService.pesquisarFuncionarios();

		ModelAndView model = new ModelAndView();
		model.addObject("listFuncionario", listFuncionario);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("funcionario-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Funcionario funcionario = funcionarioService.pesquisarFuncionarioPorId(id);

		List<Perfil> perfis = perfilService.pesquisarPerfils();

		ModelAndView model = new ModelAndView("funcionario-form");
		model.addObject("funcionario", funcionario);
		model.addObject("perfis", perfis);

		return model;
	}

	@RequestMapping(value = "/editar-senha", method = RequestMethod.GET)
	public ModelAndView editarSenha(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Funcionario funcionario = funcionarioService.pesquisarFuncionarioPorId(id);

		List<Perfil> perfis = perfilService.pesquisarPerfils();

		ModelAndView model = new ModelAndView("funcionario-form-senha");
		model.addObject("funcionario", funcionario);
		model.addObject("perfis", perfis);

		return model;
	}

	@RequestMapping(value = "/salvar-senha", method = RequestMethod.POST)
	public ModelAndView salvarSenha(@ModelAttribute Funcionario funcionario) {
		funcionarioService.alterarSenha(funcionario.getId(), funcionario.getSenha());
		List<Funcionario> listFuncionario = funcionarioService.pesquisarFuncionarios();

		ModelAndView model = new ModelAndView();
		model.addObject("listFuncionario", listFuncionario);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("funcionario-lista");
		return model;
	}

	@RequestMapping(value = "/atualizar-dados", method = RequestMethod.POST)
	public ModelAndView atualizar(@ModelAttribute Funcionario funcionario) {
		funcionarioService.atualizarDados(funcionario);

		List<Perfil> perfis = perfilService.pesquisarPerfils();

		ModelAndView model = new ModelAndView();
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("cliente-dados-pessoais-form");
		model.addObject("perfis", perfis);

		return model;
	}
}
