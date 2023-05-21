package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.model.Funcionalidade;

public interface FuncionalidadeDAO {

	public void salvar(Funcionalidade funcionalidade);

	public List<Funcionalidade> pesquisarFuncionalidades();

	public void deletar(Long idFuncionalidade);

	public Funcionalidade pesquisarFuncionalidadePorId(Long idFuncionalidade);

}
