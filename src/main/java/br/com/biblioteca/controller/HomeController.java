package br.com.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.PerfilService;
import br.com.biblioteca.service.UsuarioService;

@Controller
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView model) throws IOException {
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/meus-dados", method = RequestMethod.GET)
	public ModelAndView meusDados(HttpServletRequest request) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		
		Usuario usuarioLogado = usuarioService.pesquisarUsuarioPorId(id);
		
		ModelAndView model = new ModelAndView();
		
		if (usuarioLogado instanceof Cliente) {
			Cliente cliente = (Cliente) usuarioLogado;
			model.addObject("cliente", cliente);
			model.setViewName("cliente-dados-pessoais-form");
		} else {
			Funcionario funcionario = (Funcionario) usuarioLogado;
			model.addObject("funcionario", funcionario);
			model.setViewName("funcionario-dados-pessoais-form");
		}
		
		List<Perfil> perfis = perfilService.pesquisarPerfils();
		model.addObject("perfis", perfis);
		return model;
	}
}
