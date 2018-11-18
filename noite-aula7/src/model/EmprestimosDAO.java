package model;

import java.util.List;

interface EmprestimosDAO {
	
	public List<Emprestimos> pesquisaEmprestimos(Emprestimos e, int idUsuario);
	public void adicionaEmprestimos(Emprestimos emprestimos);
	public void atualizaEmprestimo(Emprestimos emprestimos);	
}
