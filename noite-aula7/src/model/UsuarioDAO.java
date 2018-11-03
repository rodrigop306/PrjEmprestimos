package model;

interface UsuarioDAO {
	
	public void adicionaUsuario(Usuario usuario);
	public boolean pesquisaUsuario(String login, String senha);
	public boolean logar(String login, String senha);
	
}
