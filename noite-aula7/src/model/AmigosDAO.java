package model;

import java.util.List;

interface AmigosDAO {
	
	public void adicionaAmigo(Amigos amigos);
	public List<Amigos> pesquisarAmigo(String nome, int idUsuario);
	public List<Amigos> listarAmigo();
	public void removeAmigo(int id);
	public Amigos getAmigo(String nome);
	public void editaAmigo(Amigos amigos);
}
