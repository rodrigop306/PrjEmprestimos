package model;

import java.util.List;

interface EmprestimosDAO {
	
	public List<Emprestimos> pesquisaEmprestimos(String dataDe, String dataAte);
	public void adicionaEmprestimos(Emprestimos emprestimos);
	public void atualizaEmprestimo(Emprestimos emprestimos);	
}
