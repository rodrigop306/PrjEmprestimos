package model;

public class EmprestimosRecebidos {

	private int idEmprestimosRecebidos;
	private int idObjeto;
	private int idUsuario;
	private String dataRebecimento;
	private String dataEntrega;
	private String status;
		
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdEmprestimosRecebidos() {
		return idEmprestimosRecebidos;
	}

	public void setIdEmprestimosRecebidos(int idEmprestimosRecebidos) {
		this.idEmprestimosRecebidos = idEmprestimosRecebidos;
	}

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getDataRebecimento() {
		return dataRebecimento;
	}

	public void setDataRebecimento(String dataRebecimento) {
		this.dataRebecimento = dataRebecimento;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
