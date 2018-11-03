package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class EmprestimosRealizadosDAOImpl implements EmprestimosRealizadosDAO {
	
private Connection con;
	
	public void adicionaEmprestimosRealizados(EmprestimosRealizados emprestimosRealizados) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO EMPRESTIMOSREALIZADOS (IDOBJETO, IDUSUARIO, DATARECEBIMENTO, DATAENTREGA, STATUS) "
					+ "VALUES (?,?,?,?,?)");
			int i = 0;
			ps.setInt(++i, emprestimosRealizados.getIdObjeto());
			ps.setInt(++i, emprestimosRealizados.getIdUsuario());
			ps.setDate(++i, java.sql.Date.valueOf(emprestimosRealizados.getDataRebecimento().toString()));
			ps.setString(++i, emprestimosRealizados.getDataEntrega());
			ps.setString(++i, emprestimosRealizados.getStatus());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}
	
	public List<EmprestimosRealizados> pesquisaEmprestimosRealizados(String dataDe, String dataAte) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<EmprestimosRealizados> listaEmprestimos = null;
		try {
			ps = con.prepareStatement("SELECT IDEMPRESTIMOSREALIZADOS, IDOBJETO, IDUSUARIO, DATARECEBIMENTO, DATAENTREGA, STATUS) "
					+ "FROM EMPRESTIMOSREALIZADOS WHERE DATARECEBIMENTO BETWEEN '?' AND '?' ORDER BY DATARECEBIMENTO");
			ps.setString(1, dataDe);
			ps.setString(1, dataAte);
			ResultSet rs = ps.executeQuery();
			listaEmprestimos = new ArrayList<>();
			while(rs.next()) {
				EmprestimosRealizados e = new EmprestimosRealizados();
				e.setIdEmprestimosRealizados(rs.getInt("IDEMPRESTIMOSREALIZADOS"));
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
