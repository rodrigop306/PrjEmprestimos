package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class EmprestimosRecebidosDAOImpl implements EmprestimosRecebidosDAO{
	
	private Connection con;
	
	public void adicionaEmprestimosRecebidos(EmprestimosRecebidos emprestimosRecebidos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO EMPRESTIMOSRECEBIDOS (IDOBJETO, IDUSUARIO, DATARECEBIMENTO, DATAENTREGA, STATUS) "
					+ "VALUES (?,?,?,?,?)");
			int i = 0;
			ps.setInt(++i, emprestimosRecebidos.getIdObjeto());
			ps.setInt(++i, emprestimosRecebidos.getIdUsuario());
			ps.setDate(++i, java.sql.Date.valueOf(emprestimosRecebidos.getDataRebecimento().toString()));
			ps.setString(++i, emprestimosRecebidos.getDataEntrega());
			ps.setString(++i, emprestimosRecebidos.getStatus());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}
	
	public List<EmprestimosRecebidos> pesquisaEmprestimosRecebidos(String dataDe, String dataAte) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<EmprestimosRecebidos> listaEmprestimos = null;
		try {
			ps = con.prepareStatement("SELECT IDEMPRESTIMOSRECEBIDOS, IDOBJETO, IDUSUARIO, DATARECEBIMENTO, DATAENTREGA, STATUS) "
					+ "FROM EMPRESTIMOSRECEBIDOS WHERE DATARECEBIMENTO BETWEEN '?' AND '?' ORDER BY DATARECEBIMENTO");
			ps.setString(1, dataDe);
			ps.setString(1, dataAte);
			ResultSet rs = ps.executeQuery();
			listaEmprestimos = new ArrayList<>();
			while(rs.next()) {
				EmprestimosRecebidos e = new EmprestimosRecebidos();
				e.setIdEmprestimosRecebidos(rs.getInt("IDEMPRESTIMOSRECEBIDOS"));
				e.setIdObjeto(rs.getInt("IDOBJETO"));
				e.setIdUsuario(rs.getInt("IDUSUARIO"));
				e.setDataRebecimento(rs.getString("DATARECEBIMENTO"));
				e.setDataEntrega(rs.getString("DATAENTREGA"));
				e.setStatus(rs.getString("STATUS"));
				listaEmprestimos.add(e);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return listaEmprestimos;
	}

}
