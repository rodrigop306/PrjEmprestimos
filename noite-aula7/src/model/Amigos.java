package model;

public class Amigos {

	private int idUsuario;
	private int idAmigos;
	private String nome = "";
	private String email = "";
	private String telefone = "";

	public int getIdAmigo() {
		return idAmigos;
	}

	public void setIdAmigo(int idAmigo) {
		this.idAmigos = idAmigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
