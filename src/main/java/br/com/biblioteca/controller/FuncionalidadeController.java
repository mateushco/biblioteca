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
import br.com.biblioteca.model.Menu;
import br.com.biblioteca.service.FuncionalidadeService;

@Controller
@RequestMapping("/funcionalidade")
public class FuncionalidadeController {

	@Autowired
	private FuncionalidadeService funcionalidadeService;

	public FuncionalidadeController() {
		System.out.println("FuncionalidadeController()");
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listarFuncionalidade(ModelAndView model) throws IOException {
		List<Funcionalidade> listFuncionalidade = funcionalidadeService.pesquisarFuncionalidades();
		model.addObject("listFuncionalidade", listFuncionalidade);
		model.setViewName("funcionalidade-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		Funcionalidade funcionalidade = new Funcionalidade();
		model.addObject("funcionalidade", funcionalidade);
		model.addObject("menu", Menu.values());
		model.setViewName("funcionalidade-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Funcionalidade funcionalidade) {
		
		funcionalidadeService.salvar(funcionalidade);
		List<Funcionalidade> listFuncionalidade = funcionalidadeService.pesquisarFuncionalidades();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listFuncionalidade", listFuncionalidade);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("funcionalidade-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		funcionalidadeService.deletar(id);
		
		List<Funcionalidade> listFuncionalidade = funcionalidadeService.pesquisarFuncionalidades();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listFuncionalidade", listFuncionalidade);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("funcionalidade-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Funcionalidade funcionalidade = funcionalidadeService.pesquisarFuncionalidadePorId(id);
		ModelAndView model = new ModelAndView("funcionalidade-form");
		model.addObject("menu", Menu.values());
		model.addObject("funcionalidade", funcionalidade);

		return model;
	}
}
