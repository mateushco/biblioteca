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

import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.model.Perfil;
import br.com.biblioteca.service.UsuarioService;
import br.com.biblioteca.service.PerfilService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;

	public UsuarioController() {
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(ModelAndView model) throws IOException {
		List<Usuario> listUsuario = usuarioService.pesquisarUsuarios();
		model.addObject("listUsuario", listUsuario);
		model.setViewName("usuario-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		
		List<Perfil> perfils = perfilService.pesquisarPerfils();
		
		Usuario usuario = new Usuario();
		model.addObject("perfils", perfils);
		model.addObject("usuario", usuario);
		model.setViewName("usuario-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Usuario usuario) {
		usuarioService.salvar(usuario);
		List<Usuario> listUsuario = usuarioService.pesquisarUsuarios();

		ModelAndView model = new ModelAndView();
		model.addObject("listUsuario", listUsuario);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("usuario-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		usuarioService.deletar(id);

		List<Usuario> listUsuario = usuarioService.pesquisarUsuarios();

		ModelAndView model = new ModelAndView();
		model.addObject("listUsuario", listUsuario);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("usuario-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(id);
		
		List<Perfil> perfils = perfilService.pesquisarPerfils();
		
		ModelAndView model = new ModelAndView("usuario-form");
		model.addObject("usuario", usuario);
		model.addObject("perfils", perfils);

		return model;
	}
	
	@RequestMapping(value = "/editar-senha", method = RequestMethod.GET)
	public ModelAndView editarSenha(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = usuarioService.pesquisarUsuarioPorId(id);
		ModelAndView model = new ModelAndView("usuario-form-senha");
		model.addObject("usuario", usuario);

		return model;
	}
	
	@RequestMapping(value = "/salvar-senha", method = RequestMethod.POST)
	public ModelAndView salvarSenha(@ModelAttribute Usuario usuario) {
		usuarioService.alterarSenha(usuario.getId(), usuario.getSenha());
		List<Usuario> listUsuario = usuarioService.pesquisarUsuarios();

		ModelAndView model = new ModelAndView();
		model.addObject("listUsuario", listUsuario);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("usuario-lista");
		return model;
	}
}
