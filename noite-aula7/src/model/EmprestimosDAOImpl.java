package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class EmprestimosDAOImpl implements EmprestimosDAO {

	private Connection con;

	public void adicionaEmprestimosRealizados(Emprestimos emprestimos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"INSERT INTO EMPRESTIMOS (IDEMPRESTIMOS, IDUSUARIO, NOMEOBJETO, IDAMIGOEMPRESTIMO, DATAEMPRESTIMO, "
							+ "DATADEVOLUCAO, STATUS, DETALHESEMPRESTIMOS) " + "VALUES (?,?,?,?,?,?,?,?)");
			int i = 0;
			ps.setInt(++i, emprestimos.getIdEmprestimos());
			ps.setInt(++i, emprestimos.getIdUsuario());
			ps.setString(++i, emprestimos.getNomeObjeto());
			ps.setInt(++i, emprestimos.getIdAmigoEmprestimo());
			ps.setString(++i, emprestimos.getDataEmprestimo());
			ps.setString(++i, emprestimos.getDataDevolucao());
			ps.setString(++i, emprestimos.getStatus());
			ps.setString(++i, emprestimos.getDetalhesEmprestimo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = c.fechar();
		}
	}

	public void adicionaEmprestimosRecebidos(Emprestimos emprestimos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"INSERT INTO EMPRESTIMOS (IDEMPRESTIMOS, IDUSUARIO, NOMEOBJETO, IDAMIGODONO, DATAEMPRESTIMO, "
							+ "DATADEVOLUCAO, STATUS, DETALHESEMPRESTIMOS) " + "VALUES (?,?,?,?,?,?,?,?)");
			int i = 0;
			ps.setInt(++i, emprestimos.getIdEmprestimos());
			ps.setInt(++i, emprestimos.getIdUsuario());
			ps.setString(++i, emprestimos.getNomeObjeto());
			ps.setInt(++i, emprestimos.getIdAmigoDono());
			ps.setString(++i, emprestimos.getDataEmprestimo());
			ps.setString(++i, emprestimos.getDataDevolucao());
			ps.setString(++i, emprestimos.getStatus());
			ps.setString(++i, emprestimos.getDetalhesEmprestimo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = c.fechar();
		}
	}
	
	public List<Emprestimos> pesquisaEmprestimos(String dataDe, String dataAte) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Emprestimos> listaEmprestimos = null;
		try {
			ps = con.prepareStatement("SELECT IDEMPRESTIMOS, IDUSUARIO, NOMEOBJETO, IDAMIGOEMPRESTIMO, IDAMIGODONO, DATAEMPRESTIMO, "
					+ "DATADEVOLUCAO, STATUS, DETALHESEMPRESTIMOS FROM EMPRESTIMOS "
					+ "WHERE DATAEMPRESTIMO BETWEEN ? AND ? ");
			ps.setString(1, dataDe);
			ps.setString(1, dataAte);
			ResultSet rs = ps.executeQuery();
			listaEmprestimos = new ArrayList<>();
			while (rs.next()) {
				Emprestimos e = new Emprestimos();
				e.setIdEmprestimos(rs.getInt("IDEMPRESTIMOS"));
				e.setIdUsuario(rs.getInt("IDUSUARIO"));
				e.setNomeObjeto(rs.getString("NOMEOBJETO"));
				e.setIdAmigoEmprestimo(rs.getInt("IDAMIGOEMPRESTIMO"));
				e.setIdAmigoEmprestimo(rs.getInt("IDAMIGODONO"));
				e.setDataEmprestimo(rs.getString("DATAEMPRESTIMO"));
				e.setDataDevolucao(rs.getString("DATADEVOLUCAO"));
				e.setStatus(rs.getString("STATUS"));
				e.setDetalhesEmprestimo(rs.getString("DETALHESEMPRESTIMOS"));
				listaEmprestimos.add(e);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con = c.fechar();
		}
		return listaEmprestimos;
	}

}
