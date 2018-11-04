package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	// Esse método será usado no login,
	// retornando true ou false caso o login e a senha estejam corretos
	public boolean pesquisaUsuario(String login, String senha) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT LOGIN, SENHA FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con = c.fechar();
		}
	}

	public boolean logar(String login, String senha) {
		Conexao c = new Conexao();
		con = c.abrir();
		CallableStatement cs;
		try {
			cs = con.prepareCall("{ call sp_verificausuario(?,?)}");
			cs.setString(1, login);
			cs.setString(2, senha);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con = c.fechar();
		}
	}

}
