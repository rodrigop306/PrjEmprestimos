package model;

import java.util.List;

interface EmprestimosDAO {
	
	public void adicionaEmprestimosRealizados(Emprestimos emprestimos);
	public List<Emprestimos> pesquisaEmprestimos(String dataDe, String dataAte);
	public void adicionaEmprestimosRecebidos(Emprestimos emprestimos);
}
