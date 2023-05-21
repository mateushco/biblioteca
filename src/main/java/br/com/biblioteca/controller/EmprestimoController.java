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

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private ClienteService clienteService;

	public EmprestimoController() {
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(ModelAndView model) throws IOException {
		List<Emprestimo> listEmprestimo = emprestimoService.pesquisarEmprestimos();
		model.addObject("listEmprestimo", listEmprestimo);
		model.setViewName("emprestimo-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		List<Cliente> clientes = clienteService.pesquisarClientes();
		List<Livro> livros = livroService.pesquisarLivrosDisponiveis();

		Emprestimo emprestimo = new Emprestimo();
		model.addObject("emprestimo", emprestimo);
		model.addObject("livros", livros);
		model.addObject("clientes", clientes);
		model.setViewName("emprestimo-form");
		return model;
	}

	@RequestMapping(value = "/renovar", method = RequestMethod.GET)
	public ModelAndView renovar(ModelAndView model) {
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		emprestimos = emprestimoService.pesquisarEmprestimosAtivos();

		model.addObject("emprestimos", emprestimos);
		model.setViewName("emprestimo-renovar");
		return model;
	}

	@RequestMapping(value = "/devolver", method = RequestMethod.GET)
	public ModelAndView devolver(ModelAndView model) {
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		emprestimos = emprestimoService.pesquisarEmprestimosAtivos();

		model.addObject("emprestimos", emprestimos);
		model.setViewName("emprestimo-devolver");
		return model;
	}
	
	@RequestMapping(value = "/renovar/salvar", method = RequestMethod.POST)
	public ModelAndView salvarRenovacao(@ModelAttribute Emprestimo emprestimo) {
		
		emprestimoService.renovar(emprestimo.getId());
		
		ModelAndView model = new ModelAndView();
		model.addObject("emprestimo", emprestimoService.pesquisarEmprestimoPorId(emprestimo.getId()));
		model.setViewName("emprestimo-dados");
		model.addObject("sucesso", Boolean.TRUE);
		return model;
	}
	
	@RequestMapping(value = "/devolver/salvar", method = RequestMethod.POST)
	public ModelAndView salvarDevolucao(@ModelAttribute Emprestimo emprestimo) {
		
		emprestimoService.devolver(emprestimo.getId());
		
		ModelAndView model = new ModelAndView();
		model.addObject("emprestimo", emprestimoService.pesquisarEmprestimoPorId(emprestimo.getId()));
		model.setViewName("emprestimo-dados");
		model.addObject("sucesso", Boolean.TRUE);
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Emprestimo emprestimo) {

		ModelAndView model = new ModelAndView();

		if (emprestimoService.isUsuarioTemEmprestimoAtivo(emprestimo.getIdCliente())) {

			List<Cliente> clientes = clienteService.pesquisarClientes();
			List<Livro> livros = livroService.pesquisarLivrosDisponiveis();

			model.addObject("emprestimo", emprestimo);
			model.addObject("sucesso", Boolean.FALSE);
			model.addObject("mensagem", "Empréstimo não realizado! Usuário tem empréstimo ativo.");
			model.addObject("livros", livros);
			model.addObject("clientes", clientes);
			model.setViewName("emprestimo-form");
		} else {
			emprestimoService.salvar(emprestimo);
			model.addObject("emprestimo", emprestimo);
			model.addObject("sucesso", Boolean.TRUE);
			model.setViewName("emprestimo-dados");
		}
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		emprestimoService.deletar(id);

		List<Emprestimo> listEmprestimo = emprestimoService.pesquisarEmprestimos();

		ModelAndView model = new ModelAndView();
		model.addObject("listEmprestimo", listEmprestimo);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("emprestimo-lista");
		return model;
	}

	@RequestMapping(value = "/vizualizar", method = RequestMethod.GET)
	public ModelAndView vizualizar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Emprestimo emprestimo = emprestimoService.pesquisarEmprestimoPorId(id);
		ModelAndView model = new ModelAndView("emprestimo-dados");
		model.addObject("emprestimo", emprestimo);

		return model;
	}
}
