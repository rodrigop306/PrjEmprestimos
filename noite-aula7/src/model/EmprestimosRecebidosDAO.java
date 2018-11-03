package model;

import java.util.List;

interface EmprestimosRecebidosDAO {
	
	public void adicionaEmprestimosRecebidos(EmprestimosRecebidos emprestimosRecebidos);
	public List<EmprestimosRecebidos> pesquisaEmprestimosRecebidos(String dataDe, String dataAte);
	
}
