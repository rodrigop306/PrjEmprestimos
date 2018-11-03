package model;

public class EmprestimosRealizados {
	
	private int idEmprestimosRealizados;
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

	public int getId() {
		return idEmprestimosRealizados;
	}

	public void setId(int id) {
		this.idEmprestimosRealizados = id;
	}

	public int getIdEmprestimosRealizados() {
		return idEmprestimosRealizados;
	}

	public void setIdEmprestimosRealizados(int idEmprestimosRealizados) {
		this.idEmprestimosRealizados = idEmprestimosRealizados;
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
