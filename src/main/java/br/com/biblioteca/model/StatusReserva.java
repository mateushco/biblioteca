package br.com.biblioteca.model;

public enum StatusReserva {

	ATIVO(1, "Ativo"), EFETIVADA(2, "Efetivada"), CANCELADA(3, "Cancelada");

	private Integer id;

	private String nome;

	private StatusReserva(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static StatusReserva getMenuOfId(Integer id) {
		for (StatusReserva menu : values()) {
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
