package model;

import java.util.List;

interface ObjetoDAO {
	
	public void adicionaObjeto(Objeto objeto);
	public List<Objeto> pesquisaObjeto(String nome);
	
}
