package model;

import java.util.List;

interface AmigosDAO {
	
	public void adicionaAmigo(Amigos amigos);
	public List<Amigos> pesquisarAmigo(String nome);
	public List<Amigos> listarAmigo();
	public boolean removeAmigo(String nome);
	public Amigos getAmigo(String nome);
}
