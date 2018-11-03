package model;

import java.util.List;

interface CategoriaDAO {
	
	public void adicionaCategoria(Categoria categoria);
	public List<Categoria> pesquisaCategoria(String tipo);
	
}
