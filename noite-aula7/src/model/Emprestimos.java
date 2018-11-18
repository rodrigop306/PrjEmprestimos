package model;

public class Emprestimos {

	private int idUsuario;
	private int idEmprestimos;
	private String nomeObjeto;
	private int idAmigoDono;
	private int idAmigoEmprestimo;
	private int idCategoria;
	private String dataEmprestimo;
	private String dataDevolucao;
	private String status;
	private String detalhesEmprestimo;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdEmprestimos() {
		return idEmprestimos;
	}

	public void setIdEmprestimos(int idEmprestimos) {
		this.idEmprestimos = idEmprestimos;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public int getIdAmigoDono() {
		return idAmigoDono;
	}

	public void setIdAmigoDono(int idAmigoDono) {
		this.idAmigoDono = idAmigoDono;
	}

	public int getIdAmigoEmprestimo() {
		return idAmigoEmprestimo;
	}

	public void setIdAmigoEmprestimo(int idAmigoEmprestimo) {
		this.idAmigoEmprestimo = idAmigoEmprestimo;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetalhesEmprestimo() {
		return detalhesEmprestimo;
	}

	public void setDetalhesEmprestimo(String detalhesEmprestimo) {
		this.detalhesEmprestimo = detalhesEmprestimo;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
}
