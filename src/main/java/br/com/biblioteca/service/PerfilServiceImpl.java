package br.com.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dao.FuncionalidadeDAO;
import br.com.biblioteca.dao.PerfilDAO;
import br.com.biblioteca.model.Funcionalidade;
import br.com.biblioteca.model.Perfil;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private FuncionalidadeDAO funcionalidadeDAO;

	@Override
	@Transactional
	public void salvar(Perfil perfil) {
		
		String idsFuncionalidades[] = perfil.getIdsFuncionalidades().split(",");

		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
		for (int i = 0; i < idsFuncionalidades.length; i++) {
			Funcionalidade funcionalidade = funcionalidadeDAO.pesquisarFuncionalidadePorId(Long.parseLong(idsFuncionalidades[i]));
			funcionalidades.add(funcionalidade);
		}
		perfil.setFuncionalidades(funcionalidades);
		perfilDAO.salvar(perfil);
	}

	@Override
	@Transactional
	public List<Perfil> pesquisarPerfils() {
		return perfilDAO.pesquisarPerfils();
	}

	@Override
	@Transactional
	public void deletar(Long idPerfil) {
		perfilDAO.deletar(idPerfil);
	}

	@Override
	@Transactional
	public Perfil pesquisarPerfilPorId(Long idPerfil) {
		return perfilDAO.pesquisarPerfilPorId(idPerfil);
	}

}