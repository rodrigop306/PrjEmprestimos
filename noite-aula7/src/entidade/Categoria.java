package entidade;

public class Categoria {

	private int idCategoria;
	private String tipo;

	public int getId() {
		return idCategoria;
	}

	public void setId(int id) {
		this.idCategoria = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() { 
		StringBuffer s = new StringBuffer();
		s.append("Id:");
		s.append(this.getId());
		s.append("\tTipo:");
		s.append(this.getTipo());
		return s.toString();
	}

}
