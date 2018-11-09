package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;

public class UsuarioDAOImpl implements UsuarioDAO{

	private Connection con;

	public void adicionaUsuario(Usuario usuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO USUARIO (IDUSUARIO, LOGIN, SENHA, NOME, DATACADASTRO) VALUES (SEQUENCIA_PK_USUARIO.nextVal,?,?,?,?)");
			int i = 0;
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			ps.setString(++i, usuario.getNome());
			ps.setString(++i, usuario.getDataCadastro());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível adicionar usuário.");
		} finally {
			con = c.fechar();
		}
	}

	public Usuario getUsuario(Usuario usuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		Usuario user = null;
		try {
			ps = con.prepareStatement("SELECT IDUSUARIO, LOGIN, NOME FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user = new Usuario();
				user.setIdUsuario(rs.getInt("IDUSUARIO"));
				user.setLogin(rs.getString("LOGIN"));
				user.setNome(rs.getString("NOME"));
			}
			con = c.fechar();
			ps.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean logar(Usuario usuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		CallableStatement cs;
		try {
			cs = con.prepareCall("{ call sp_verificausuario(?,?)}");
			cs.setString(1, usuario.getLogin());
			cs.setString(2, usuario.getSenha());
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con = c.fechar();
		}
	}

	@Override
	public boolean existeUsuario(String login) {
		Conexao c = new Conexao();
		con = c.abrir();
		try {
			Statement ps = con.createStatement();
			String sql = "SELECT * FROM USUARIO WHERE LOGIN = '"+login+"' ";
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

}
