package model;

interface UsuarioDAO {
	
	public void adicionaUsuario(Usuario usuario);
	public Usuario getUsuario(Usuario usuario);
	public boolean logar(Usuario usuario);
	public boolean existeUsuario(String login);
}
