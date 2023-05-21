package br.com.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Funcionalidade;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.service.FuncionalidadeService;
import br.com.biblioteca.service.PerfilService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private FuncionalidadeService funcionalidadeService;

	public PerfilController() {
		System.out.println("PerfilController()");
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listarPerfil(ModelAndView model) throws IOException {
		List<Perfil> listPerfil = perfilService.pesquisarPerfils();
		model.addObject("listPerfil", listPerfil);
		model.setViewName("perfil-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		
		List<Funcionalidade> funcionalidades = funcionalidadeService.pesquisarFuncionalidades();
		
		Perfil perfil = new Perfil();
		model.addObject("perfil", perfil);
		model.addObject("funcionalidades", funcionalidades);
		model.setViewName("perfil-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Perfil perfil) {
		
		perfilService.salvar(perfil);
		List<Perfil> listPerfil = perfilService.pesquisarPerfils();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listPerfil", listPerfil);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("perfil-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		perfilService.deletar(id);
		
		List<Perfil> listPerfil = perfilService.pesquisarPerfils();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listPerfil", listPerfil);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("perfil-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		Perfil perfil = perfilService.pesquisarPerfilPorId(id);
		
		List<Funcionalidade> funcionalidades = funcionalidadeService.pesquisarFuncionalidades();
		
		for (Funcionalidade funcionalidade : funcionalidades) {
			for (Funcionalidade funcionalidadePerfil : perfil.getFuncionalidades()) {
				if (funcionalidade.getId() == funcionalidadePerfil.getId()) {
					funcionalidade.setSelecionado(true);
				}
			}
		}
		
		ModelAndView model = new ModelAndView("perfil-form");
		model.addObject("funcionalidades", funcionalidades);
		model.addObject("perfil", perfil);

		return model;
	}
}
