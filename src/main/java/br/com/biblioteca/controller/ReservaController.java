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
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.StatusReserva;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.ReservaService;
import br.com.biblioteca.service.LivroService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmprestimoService emprestimoService;

	public ReservaController() {
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(HttpServletRequest request, ModelAndView model) throws IOException {
		
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
		List<Reserva> listReserva = new ArrayList<Reserva>();
		
		if (usuarioLogado instanceof Cliente)
			listReserva = reservaService.pesquisarReservasUsuarioLogado(usuarioLogado.getId());
		else
			listReserva = reservaService.pesquisarReservas();
		
		model.addObject("listReserva", listReserva);
		model.setViewName("reserva-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(HttpServletRequest request, ModelAndView model) {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Cliente> clientes = clienteService.pesquisarClientes();
		List<Livro> livros = livroService.pesquisarLivros();

		Reserva reserva = new Reserva();
		model.addObject("reserva", reserva);
		model.addObject("livros", livros);
		model.addObject("clientes", clientes);
		model.addObject("usuarioLogado", usuarioLogado);
		model.setViewName("reserva-form");
		return model;
	}

	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView cancelar(ModelAndView model) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas = reservaService.pesquisarReservasAtivos();

		model.addObject("reservas", reservas);
		model.setViewName("reserva-cancelar");
		return model;
	}

	@RequestMapping(value = "/cancelar/salvar", method = RequestMethod.POST)
	public ModelAndView salvarCancelar(@ModelAttribute Reserva reserva) {
		
		reservaService.cancelar(reserva.getId());
		
		ModelAndView model = new ModelAndView();
		model.addObject("reserva", reservaService.pesquisarReservaPorId(reserva.getId()));
		model.setViewName("reserva-dados");
		model.addObject("sucesso", Boolean.TRUE);
		return model;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Reserva reserva) {

		ModelAndView model = new ModelAndView();

		if (reservaService.isUsuarioTemReservaAtivo(reserva.getIdCliente())) {

			List<Cliente> clientes = clienteService.pesquisarClientes();
			List<Livro> livros = livroService.pesquisarLivros();

			model.addObject("reserva", reserva);
			model.addObject("sucesso", Boolean.FALSE);
			model.addObject("mensagem", "Reserva não realizada! Usuário tem uma reserva ativa.");
			model.addObject("livros", livros);
			model.addObject("clientes", clientes);
			model.setViewName("reserva-form");
		} else {
			reserva.setStatus(StatusReserva.ATIVO);
			reservaService.salvar(reserva);
			model.addObject("reserva", reserva);
			model.addObject("sucesso", Boolean.TRUE);
			model.setViewName("reserva-dados");
		}
		return model;
	}

	@RequestMapping(value = "/vizualizar", method = RequestMethod.GET)
	public ModelAndView vizualizar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Reserva reserva = reservaService.pesquisarReservaPorId(id);
		ModelAndView model = new ModelAndView("reserva-dados");
		model.addObject("reserva", reserva);

		return model;
	}
	
	@RequestMapping(value = "/efetivar", method = RequestMethod.GET)
	public ModelAndView efetivar(ModelAndView model) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas = reservaService.pesquisarReservasAtivos();

		model.addObject("reservas", reservas);
		model.setViewName("reserva-efetivar-emprestimo");
		return model;
	}
	
	@RequestMapping(value = "/efetivar/salvar", method = RequestMethod.POST)
	public ModelAndView salvarEfetivar(@ModelAttribute Reserva reserva) {
		
		
		ModelAndView model = new ModelAndView();
		
		Reserva reservaBD = reservaService.pesquisarReservaPorId(reserva.getId());
		
		if (emprestimoService.isUsuarioTemEmprestimoAtivo(reservaBD.getCliente().getId())) {

			model.addObject("sucesso", Boolean.FALSE);
			model.addObject("mensagem", "A reserva não foi efetivada! Usuário tem empréstimo ativo.");
			model.setViewName("reserva-efetivar-emprestimo");
		} else {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setIdCliente(reservaBD.getCliente().getId());
			
			String idsLivros = "";
			for (int i = 0; i < reservaBD.getLivros().size(); i++) {
				idsLivros += reservaBD.getLivros().get(i).getId() + ",";
			}
			
			emprestimo.setIdsLivros(idsLivros);
			emprestimoService.salvar(emprestimo);
			model.addObject("emprestimo", emprestimo);
			model.addObject("sucesso", Boolean.TRUE);
			model.setViewName("emprestimo-dados");
			reservaService.efetivar(reservaBD);
		}
		return model;
	}
}
