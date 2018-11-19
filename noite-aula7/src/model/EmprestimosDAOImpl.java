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

	public void adicionaEmprestimos(Emprestimos emprestimos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			if (emprestimos.getIdAmigoDono() != 0) {
				ps = con.prepareStatement(
						"INSERT INTO EMPRESTIMOS (IDEMPRESTIMOS, IDUSUARIO, NOMEOBJETO, IDAMIGODONO, DATAEMPRESTIMO, "
								+ "DATADEVOLUCAO, STATUS, DETALHESEMPRESTIMOS, IDCATEGORIA) "
								+ "VALUES (SEQUENCIA_PK_EMPRESTIMOS.nextVal,?,?,?,?,?,?,?,?)");
				int i = 0;
				ps.setInt(++i, emprestimos.getIdUsuario());
				ps.setString(++i, emprestimos.getNomeObjeto());
				ps.setInt(++i, emprestimos.getIdAmigoDono());
				ps.setString(++i, emprestimos.getDataEmprestimo());
				ps.setString(++i, emprestimos.getDataDevolucao());
				ps.setString(++i, emprestimos.getStatus());
				ps.setString(++i, emprestimos.getDetalhesEmprestimo());
				ps.setInt(++i, emprestimos.getIdCategoria());
				ps.executeUpdate();
				ps.close();
			} else if (emprestimos.getIdAmigoEmprestimo() != 0) {
				ps = con.prepareStatement(
						"INSERT INTO EMPRESTIMOS (IDEMPRESTIMOS, IDUSUARIO, NOMEOBJETO, IDAMIGOEMPRESTIMO, DATAEMPRESTIMO, "
								+ "DATADEVOLUCAO, STATUS, DETALHESEMPRESTIMOS, IDCATEGORIA) "
								+ "VALUES (SEQUENCIA_PK_EMPRESTIMOS.nextVal,?,?,?,?,?,?,?,?)");
				int i = 0;
				ps.setInt(++i, emprestimos.getIdUsuario());
				ps.setString(++i, emprestimos.getNomeObjeto());
				ps.setInt(++i, emprestimos.getIdAmigoEmprestimo());
				ps.setString(++i, emprestimos.getDataEmprestimo());
				ps.setString(++i, emprestimos.getDataDevolucao());
				ps.setString(++i, emprestimos.getStatus());
				ps.setString(++i, emprestimos.getDetalhesEmprestimo());
				ps.setInt(++i, emprestimos.getIdCategoria());
				ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}

	public List<Emprestimos> pesquisaEmprestimos(Emprestimos e, int idUsuario) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		List<Emprestimos> listaEmprestimos = null;
		try {
			if (e.getIdAmigoDono() != 0) {
				ps = con.prepareStatement(
						"SELECT e.idusuario, e.idemprestimos, e.nomeobjeto, c.idcategoria, c.tipo, a.nome, a.idamigo, e.dataemprestimo, e.datadevolucao, e.status, e.detalhesemprestimos FROM EMPRESTIMOS e, AMIGOS a, CATEGORIA c WHERE e.idamigodono = a.idamigo "
								+ "AND e.idcategoria = c.idcategoria AND e.idUsuario = ? AND e.nomeobjeto LIKE ? AND a.idamigo = ?");
				ps.setInt(1, idUsuario);
				ps.setString(2, "%" + e.getNomeObjeto() + "%");
				ps.setInt(3, e.getIdAmigoDono());
				ResultSet rs = ps.executeQuery();
				listaEmprestimos = new ArrayList<>();
				while (rs.next()) {
					Emprestimos e1 = new Emprestimos();
					e1.setIdEmprestimos(rs.getInt("idemprestimos"));
					e1.setIdUsuario(rs.getInt("idusuario"));
					e1.setNomeObjeto(rs.getString("nomeobjeto"));
					e1.setIdCategoria(rs.getInt("idcategoria"));
					e1.setNomeCategoria(rs.getString("tipo"));
					e1.setIdAmigoDono(rs.getInt("idamigo"));
					e1.setNomeAmigo(rs.getString("nome"));
					e1.setDataEmprestimo(rs.getString("dataemprestimo"));
					e1.setDataDevolucao(rs.getString("datadevolucao"));
					e1.setStatus(rs.getString("status"));
					e1.setDetalhesEmprestimo(rs.getString("detalhesemprestimos"));
					listaEmprestimos.add(e1);
				}
				rs.close();
				ps.close();
			} else if (e.getIdAmigoEmprestimo() != 0) {
				ps = con.prepareStatement(
						"SELECT e.idusuario, e.idemprestimos, e.nomeobjeto, c.idcategoria, c.tipo, a.nome, a.idamigo, e.dataemprestimo, e.datadevolucao, e.status, e.detalhesemprestimos FROM EMPRESTIMOS e, AMIGOS a, CATEGORIA c WHERE e.IDAMIGOEMPRESTIMO = a.idamigo "
								+ "AND e.idcategoria = c.idcategoria AND e.idUsuario = ? AND e.nomeobjeto LIKE ? AND a.idamigo = ?");
				ps.setInt(1, idUsuario);
				ps.setString(2, "%" + e.getNomeObjeto() + "%");
				ps.setInt(3, e.getIdAmigoEmprestimo());
				ResultSet rs = ps.executeQuery();
				listaEmprestimos = new ArrayList<>();
				while (rs.next()) {
					Emprestimos e1 = new Emprestimos();
					e1.setIdEmprestimos(rs.getInt("idemprestimos"));
					e1.setIdUsuario(rs.getInt("idusuario"));
					e1.setNomeObjeto(rs.getString("nomeobjeto"));
					e1.setIdCategoria(rs.getInt("idcategoria"));
					e1.setNomeCategoria(rs.getString("tipo"));
					e1.setIdAmigoEmprestimo(rs.getInt("idamigo"));
					e1.setNomeAmigo(rs.getString("nome"));
					e1.setDataEmprestimo(rs.getString("dataemprestimo"));
					e1.setDataDevolucao(rs.getString("datadevolucao"));
					e1.setStatus(rs.getString("status"));
					e1.setDetalhesEmprestimo(rs.getString("detalhesemprestimos"));
					listaEmprestimos.add(e1);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		con = c.fechar();
		return listaEmprestimos;
	}
	
	
	
	public Emprestimos pesquisaPorId(int idEmprestimos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		Emprestimos listaEmprestimos = null;
		try {
				ps = con.prepareStatement(
						"SELECT e.idusuario, e.idemprestimos, e.nomeobjeto, c.idcategoria, c.tipo, e.idamigodono, e.idamigoemprestimo, e.dataemprestimo, e.datadevolucao, e.status, e.detalhesemprestimos, a.nome FROM EMPRESTIMOS e, CATEGORIA c, AMIGOS a WHERE "
								+ "e.idcategoria = c.idcategoria AND e.idemprestimos = ? AND (e.idamigodono = a.idamigo OR e.idamigoemprestimo = a.idamigo)");
				ps.setInt(1, idEmprestimos);
				ResultSet rs = ps.executeQuery();
				listaEmprestimos = new Emprestimos();
				if (rs.next()) {
					Emprestimos e1 = new Emprestimos();
					e1.setIdEmprestimos(rs.getInt("idemprestimos"));
					e1.setIdUsuario(rs.getInt("idusuario"));
					e1.setNomeObjeto(rs.getString("nomeobjeto"));
					e1.setIdCategoria(rs.getInt("idcategoria"));
					e1.setNomeCategoria(rs.getString("tipo"));
					System.out.println(rs.getString("tipo"));
					if(rs.getInt("idamigodono")!=0) {
						e1.setIdAmigoDono(rs.getInt("idamigodono"));
					}else {
						e1.setIdAmigoEmprestimo(rs.getInt("idamigoemprestimo"));
					}
					e1.setNomeAmigo(rs.getString("nome"));
					System.out.println(rs.getString("nome"));
					e1.setDataEmprestimo(rs.getString("dataemprestimo"));
					e1.setDataDevolucao(rs.getString("datadevolucao"));
					e1.setStatus(rs.getString("status"));
					e1.setDetalhesEmprestimo(rs.getString("detalhesemprestimos"));
					listaEmprestimos=e1;
				}
				rs.close();
				ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		con = c.fechar();
		return listaEmprestimos;
	}
	
	
	
	public void atualizaEmprestimo(Emprestimos emprestimos) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			if (emprestimos.getIdAmigoDono() != 0) {
				ps = con.prepareStatement(
						"UPDATE EMPRESTIMOS SET NOMEOBJETO = ?, DATAEMPRESTIMO = ?, DATADEVOLUCAO = ?, "
								+ "STATUS = ?, DETALHESEMPRESTIMOS = ?, IDAMIGODONO = ?, IDCATEGORIA = ? WHERE IDEMPRESTIMOS = ? "
								+ "AND IDUSUARIO = ? ");
				int i = 0;
				ps.setString(++i, emprestimos.getNomeObjeto());
				ps.setString(++i, emprestimos.getDataEmprestimo());
				ps.setString(++i, emprestimos.getDataDevolucao());
				ps.setString(++i, emprestimos.getStatus());
				ps.setString(++i, emprestimos.getDetalhesEmprestimo());
				ps.setInt(++i, emprestimos.getIdAmigoDono());
				ps.setInt(++i, emprestimos.getIdCategoria());
				ps.setInt(++i, emprestimos.getIdEmprestimos());
				ps.setInt(++i, emprestimos.getIdUsuario());
				ps.executeUpdate();
				ps.close();
			} else if (emprestimos.getIdAmigoEmprestimo() != 0) {
				ps = con.prepareStatement(
						"UPDATE EMPRESTIMOS SET NOMEOBJETO = ?, DATAEMPRESTIMO = ?, DATADEVOLUCAO = ?, "
								+ "STATUS = ?, DETALHESEMPRESTIMOS = ?, IDAMIGOEMPRESTIMO =?, IDCATEGORIA = ? WHERE IDEMPRESTIMOS = ? "
								+ "AND IDUSUARIO = ? ");
				int i = 0;
				ps.setString(++i, emprestimos.getNomeObjeto());
				ps.setString(++i, emprestimos.getDataEmprestimo());
				ps.setString(++i, emprestimos.getDataDevolucao());
				ps.setString(++i, emprestimos.getStatus());
				ps.setString(++i, emprestimos.getDetalhesEmprestimo());
				ps.setInt(++i, emprestimos.getIdAmigoEmprestimo());
				ps.setInt(++i, emprestimos.getIdCategoria());
				ps.setInt(++i, emprestimos.getIdEmprestimos());
				ps.setInt(++i, emprestimos.getIdUsuario());
				ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
	}

	public boolean removeEmprestimo(int idEmp) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM EMPRESTIMOS WHERE IDEMPRESTIMOS = ? ");
			ps.setInt(1, idEmp);
			ps.execute();
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = c.fechar();
		return false;
	}

}
