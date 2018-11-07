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
			ps = con.prepareStatement("INSERT INTO AMIGOS (NOME, EMAIL, TELEFONE) VALUES (?,?,?)");
			int i = 0;
			ps.setString(++i, amigos.getNome());
			ps.setString(++i, amigos.getEmail());
			ps.setString(++i, amigos.getTelefone());
			ps.executeUpdate();
			ps.close();
			con = c.fechar();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Amigos> pesquisarAmigo(String nome) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Amigos> listaAmigos = null;
		try {
			ps = con.prepareStatement("SELECT A.NOME, A.EMAIL, A.TELEFONE FROM AMIGOS A INNER JOIN USUARIO U ON U.IDUSUARIO = A.IDAMIGOS WHERE NOME LIKE ?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			listaAmigos = new ArrayList<>();
			while(rs.next()) {
				Amigos a = new Amigos();
				a.setNome(rs.getString("NOME"));
				a.setEmail(rs.getString("EMAIL"));
				a.setTelefone(rs.getString("TELEFONE"));
				listaAmigos.add(a);
			}
			rs.close();
			ps.close();
			con = c.fechar();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return listaAmigos;
	}

	public boolean removeAmigo(String nome) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM AMIGOS WHERE NOME = ?");
			ps.setString(1, nome);
			ps.execute();
			ps.close();
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		con = c.fechar();
		return true;
	}

	// Será usado para pegar o ID do amigo para realizar o empréstimo
	// Essa query está em Oracle, ver com o prof. Joilson como será realizado a query
	public Amigos getAmigo(String nome){
		Conexao c = new Conexao();
		con = c.abrir();
		Amigos a = null;
		try {
			Statement ps = con.createStatement();
			String sql = "SELECT IDAMIGO, IDUSUARIO, NOME, EMAIL, TELEFONE FROM AMIGOS WHERE NOME = '"+nome+"' ";
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
				System.out.println("Passei por aqui.");
				a = new Amigos();
				a.setIdAmigo(rs.getInt("IDAMIGO"));
				a.setNome(rs.getString("NOME"));
				a.setEmail(rs.getString("EMAIL"));
				a.setTelefone(rs.getString("TELEFONE"));
				return a;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return a;
	}


}
