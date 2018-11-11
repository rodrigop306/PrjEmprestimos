package model;

import java.util.List;

interface EmprestimosDAO {
	
	public List<Emprestimos> pesquisaEmprestimos(String dataDe, String dataAte);
	public void adicionaEmprestimosRecebidos(Emprestimos emprestimos);
	public void atualizaEmprestimo(Emprestimos emprestimos);
	public void adicionaEmprestimosRealizados(Emprestimos emprestimos);
	
}
