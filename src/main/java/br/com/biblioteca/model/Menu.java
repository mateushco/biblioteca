package br.com.biblioteca.model;

public enum Menu {

	EMPRESTIMO(1, "Empr�stimo"), CADASTROS(2, "Cadastros"), ADMINISTRACAO(3, "Administra��o"), RESERVA(4, "Reserva"), RELATORIO(5, "Relat�rio");

	private Integer id;

	private String nome;

	private Menu(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static Menu getMenuOfId(Integer id) {
		for (Menu menu : values()) {
			if (menu.getId() == id) {
				return menu;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
