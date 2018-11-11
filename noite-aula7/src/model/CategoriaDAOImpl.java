package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class CategoriaDAOImpl implements CategoriaDAO {

	private Connection con;

	public void adicionaCategoria(Categoria categoria) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO CATEGORIA (IDCATEGORIA, TIPO) VALUES (SEQUENCIA_PK_CATEGORIA.nextVal, ?)");
			ps.setString(1, categoria.getTipo());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}

	public List<Categoria> pesquisaCategoria(String tipo) {
		Conexao c = new Conexao();
		con = c.abrir();
		List<Categoria> listaCategoria = null;
		try {
			Statement ps = con.createStatement();
			String sql = "SELECT IDCATEGORIA, TIPO FROM CATEGORIA WHERE TIPO LIKE '%"+tipo+"%' ";
			ResultSet rs = ps.executeQuery(sql);
			listaCategoria = new ArrayList<>();
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("IDCATEGORIA"));
				categoria.setTipo(rs.getString("TIPO"));
				listaCategoria.add(categoria);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
		return listaCategoria;
	}

	@Override
	public List<Categoria> listaCategoria() {
		Conexao c = new Conexao();
		con = c.abrir();
		List<Categoria> lista = null;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT IDCATEGORIA, TIPO FROM CATEGORIA");
			ResultSet rs = ps.executeQuery();
			lista = new ArrayList<>();
			while(rs.next()){
				Categoria cat = new Categoria();
				cat.setId(rs.getInt("IDCATEGORIA"));
				cat.setTipo(rs.getString("IDCATEGORIA"));
				lista.add(cat);
			}
			ps.close();
			rs.close();
			return lista;
		} catch(SQLException e){
			e.printStackTrace();
		}
		con = c.fechar();
		return lista;
	}

	public boolean removeCategoria(Categoria categoria) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM CATEGORIA WHERE IDCATEGORIA = '"+categoria.getId()+"' ");
			ps.execute();
			ps.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
		}
		con = c.fechar();
		return false;
	}

	@Override
	public void editarCategoria(Categoria categoria) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE CATEGORIA SET TIPO = '"+categoria.getTipo()+"' WHERE IDCATEGORIA = "+categoria.getId()+" ");
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e){
			e.printStackTrace();
		} 
		con = c.fechar();
	}

}
