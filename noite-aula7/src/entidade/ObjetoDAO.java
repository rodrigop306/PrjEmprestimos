package entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class ObjetoDAO {
	
	private Connection con;
	
	public void adicionaObjeto(Objeto objeto) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO OBJETO (IDCATEGORIA, NOME, DESCRICAO) VALUES (?,?,?)");
			int i = 0;
			ps.setInt(++i, objeto.getIdCategoria());
			ps.setString(++i, objeto.getNome());
			ps.setString(++i, objeto.getDescricao());
			ps.executeUpdate();
			ps.close();
			con = c.fechar();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Objeto> pesquisaObjeto(String nome){
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Objeto> listaObjetos = null;
		try {
			ps = con.prepareStatement("SELECT IDOBJETO, IDCATEGORIA, NOME, DESCRICAO FROM OBJETO WHERE NOME LIKE ?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			listaObjetos = new ArrayList<>();
			while(rs.next()) {
				Objeto o = new Objeto();
				o.setIdObjeto(rs.getInt("IDOBJETO"));
				o.setIdCategoria(rs.getInt("IDCATEGORIA"));
				o.setNome(rs.getString("NOME"));
				o.setDescricao(rs.getString("DESCRICAO"));
				listaObjetos.add(o);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return listaObjetos;
	}
	
}
