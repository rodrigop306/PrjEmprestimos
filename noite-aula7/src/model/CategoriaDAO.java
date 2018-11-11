package model;

import java.util.List;

interface CategoriaDAO {
	
	public void adicionaCategoria(Categoria categoria);
	public List<Categoria> pesquisaCategoria(String tipo);
	public List<Categoria> listaCategoria();
	public boolean removeCategoria(Categoria categoria);
	public void editarCategoria(Categoria categoria);
	
}
