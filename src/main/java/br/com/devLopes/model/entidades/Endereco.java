package br.com.devLopes.model.entidades;

public class Endereco {

	private int id;

	private String rua;

	private String cidade;

	private String estado;

	private int usuario_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId_usuario() {
		return usuario_id;
	}

	public void setId_usuario(int id_usuario) {
		this.usuario_id = id_usuario;
	}

}
