package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class AmigosDAOImpl implements AmigosDAO{

	private Connection con;
	
	public void adicionaAmigo(Amigos amigos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO AMIGOS (IDAMIGO, IDUSUARIO, NOME, EMAIL, TELEFONE) VALUES (SEQUENCIA_PK_AMIGOS.nextVal,?,?,?,?)");
			int i = 0;
			ps.setInt(++i, amigos.getIdUsuario());
			ps.setString(++i, amigos.getNome());
			ps.setString(++i, amigos.getEmail());
			ps.setString(++i, amigos.getTelefone());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}

	public List<Amigos> pesquisarAmigo(String nome, int idUsuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Amigos> listaAmigos = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT A.IDAMIGO, A.IDUSUARIO, A.NOME, A.EMAIL, A.TELEFONE FROM AMIGOS A INNER JOIN USUARIO U ON U.IDUSUARIO = A.IDUSUARIO WHERE A.NOME LIKE '%"+nome+"%' AND A.IDUSUARIO = ? ");
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Amigos a = new Amigos();
				a.setIdAmigo(rs.getInt("IDAMIGO"));
				a.setIdUsuario(rs.getInt("IDUSUARIO"));
				a.setNome(rs.getString("NOME"));
				a.setEmail(rs.getString("EMAIL"));
				a.setTelefone(rs.getString("TELEFONE"));
				listaAmigos.add(a);
			}
			rs.close();
			ps.close();
			return listaAmigos;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
		return null;
	}

	public boolean removeAmigo(String nome) {
		Conexao c = new Conexao();
		PreparedStatement ps;
		try {
			Amigos a = getAmigo(nome);
			if(a == null){
				return false;
			} else {
				con = c.abrir();
				ps = con.prepareStatement("DELETE FROM AMIGOS WHERE IDAMIGO = ?");
				ps.setInt(1, a.getIdAmigo());
				ps.execute();
				ps.close();
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		con = c.fechar();
		return false;
	}

	public Amigos getAmigo(String nome){
		Conexao c = new Conexao();
		con = c.abrir();
		Amigos a = null;
		try {
			Statement ps = con.createStatement();
			String sql = "SELECT IDAMIGO, IDUSUARIO, NOME, EMAIL, TELEFONE FROM AMIGOS WHERE NOME = '"+nome+"' ";
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
				a = new Amigos();
				a.setIdAmigo(rs.getInt("IDAMIGO"));
				a.setNome(rs.getString("NOME"));
				a.setEmail(rs.getString("EMAIL"));
				a.setTelefone(rs.getString("TELEFONE"));
				con = c.fechar();
				ps.close();
				rs.close();
				return a;
			}
			ps.close();
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		con = c.fechar();
		return a;
	}

	@Override
	public List<Amigos> listarAmigo() {
		Conexao c = new Conexao();
		con = c.abrir();
		List<Amigos> lista = null;
		try {
			Statement ps = con.createStatement();
			String sql = "SELECT A.IDAMIGO, A.IDUSUARIO, A.NOME, A.EMAIL, A.TELEFONE FROM AMIGOS A INNER JOIN USUARIO U ON U.IDUSUARIO = A.IDUSUARIO";
			ResultSet rs = ps.executeQuery(sql);
			lista = new ArrayList<>();
			while(rs.next()){
				Amigos a = new Amigos();
				a.setIdAmigo(rs.getInt("IDUSUARIO"));
				a.setIdUsuario(rs.getInt("IDUSUARIO"));
				a.setNome(rs.getString("NOME"));
				a.setEmail(rs.getString("EMAIL"));
				a.setTelefone(rs.getString("TELEFONE"));
				lista.add(a);
			}
			return lista;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return lista;
	}

}
