package br.com.biblioteca.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Funcionalidade;
import br.com.biblioteca.model.Menu;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "login")
	public ModelAndView login(Usuario usuario, HttpSession session) throws IOException, GeneralSecurityException {

		Usuario usuarioGoogle = new Usuario();
		Usuario usuarioLogado = new Usuario();
		boolean isCompletarCadastro = false;
		
		if(usuario.getCredential() != null && !usuario.getCredential().isEmpty()) {
			usuarioGoogle = loginService.extractGoogleLoginInfo(usuario.getCredential());
			usuarioLogado = loginService.realizarLoginGoogle(usuarioGoogle.getLogin());
			
			if (usuarioLogado == null) {
				Cliente cliente = new Cliente();
				cliente.setNome(usuarioGoogle.getNome());
				cliente.setEmail(usuarioGoogle.getEmail());
				cliente.setLogin(usuarioGoogle.getLogin());
				clienteService.salvarExterno(cliente);
				isCompletarCadastro = true;
				usuarioLogado = loginService.realizarLoginGoogle(usuarioGoogle.getLogin());
			}
				
		} else {
			usuarioLogado = loginService.realizarLogin(usuario.getLogin(), usuario.getSenha());
		}

		ModelAndView model = new ModelAndView();

		if (usuarioLogado != null || (usuario.getCredential() != null && !usuario.getCredential().isEmpty())) {

			List<Funcionalidade> funcionalidadesAdministracao = new ArrayList<Funcionalidade>();
			usuarioLogado.getPerfil().getFuncionalidades().stream()
					.sorted(Comparator.comparingInt(Funcionalidade::getOrdemMenu))
					.filter(i -> i.getMenu().getId() == Menu.ADMINISTRACAO.getId())
					.forEach(i -> funcionalidadesAdministracao.add(i));

			List<Funcionalidade> funcionalidadesEmprestimo = new ArrayList<Funcionalidade>();
			usuarioLogado.getPerfil().getFuncionalidades().stream()
					.sorted(Comparator.comparingInt(Funcionalidade::getOrdemMenu))
					.filter(i -> i.getMenu().getId() == Menu.EMPRESTIMO.getId())
					.forEach(i -> funcionalidadesEmprestimo.add(i));

			List<Funcionalidade> funcionalidadesCadastro = new ArrayList<Funcionalidade>();
			usuarioLogado.getPerfil().getFuncionalidades().stream()
					.sorted(Comparator.comparingInt(Funcionalidade::getOrdemMenu))
					.filter(i -> i.getMenu().getId() == Menu.CADASTROS.getId())
					.forEach(i -> funcionalidadesCadastro.add(i));
			
			List<Funcionalidade> funcionalidadesReserva = new ArrayList<Funcionalidade>();
			usuarioLogado.getPerfil().getFuncionalidades().stream()
					.sorted(Comparator.comparingInt(Funcionalidade::getOrdemMenu))
					.filter(i -> i.getMenu().getId() == Menu.RESERVA.getId())
					.forEach(i -> funcionalidadesReserva.add(i));
			
			List<Funcionalidade> funcionalidadesRelatorio = new ArrayList<Funcionalidade>();
			usuarioLogado.getPerfil().getFuncionalidades().stream()
					.sorted(Comparator.comparingInt(Funcionalidade::getOrdemMenu))
					.filter(i -> i.getMenu().getId() == Menu.RELATORIO.getId())
					.forEach(i -> funcionalidadesRelatorio.add(i));

			session.setAttribute("usuarioLogado", usuarioLogado);
			session.setAttribute("funcionalidadesAdministracao", funcionalidadesAdministracao);
			session.setAttribute("funcionalidadesEmprestimo", funcionalidadesEmprestimo);
			session.setAttribute("funcionalidadesCadastro", funcionalidadesCadastro);
			session.setAttribute("funcionalidadesReserva", funcionalidadesReserva);
			session.setAttribute("funcionalidadesRelatorio", funcionalidadesRelatorio);

			model.addObject("sucesso", Boolean.TRUE);
			
			if (isCompletarCadastro)
				model.setViewName("cadastro-completar");
			else
				model.setViewName("principal");
			return model;
		}

		model.addObject("sucesso", Boolean.FALSE);
		model.setViewName("index");
		return model;
	}

	@RequestMapping("sair")
	public ModelAndView sair(HttpSession session) {
		session.invalidate();

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

}
