package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ps = con.prepareStatement("INSERT INTO CATEGORIA (TIPO) VALUE ?");
			int i = 0;
			ps.setString(++i, categoria.getTipo());
			ps.executeUpdate();
			ps.close();
			con = c.fechar();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Categoria> pesquisaCategoria(String tipo) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Categoria> listaCategoria = null;
		try {
			ps = con.prepareStatement("SELECT TIPO FROM CATEGORIA WHERE TIPO LIKE ?");
			ps.setString(1, tipo);
			ResultSet rs = ps.executeQuery();
			listaCategoria = new ArrayList<>();
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setTipo(rs.getString("TIPO"));
				listaCategoria.add(categoria);
			}
			rs.close();
			ps.close();
			con = c.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategoria;
	}

}
