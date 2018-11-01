package entidade;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;

public class UsuarioDAO {

	private Connection con;

	public void adicionaUsuario(Usuario usuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO USUARIO (LOGIN, SENHA, NOME, DATACADASTRO) VALUES (?,?,?,?)");
			int i = 0;
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			ps.setString(++i, usuario.getNome());
			ps.setString(++i, usuario.getDataCadastro());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}

	// Esse método será usado no login,
	// retornando true ou false caso o login e a senha estejam corretos
	public boolean pesquisaUsuario(String login, String senha) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT 1 FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
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
			cs = con.prepareCall("{procedure(?,?)}");
			int i=0;
			cs.setString(++i, login);
			cs.setString(++i, senha);
			cs.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			con = c.fechar();
		}
		
	}

}
