package model;

import java.util.List;

interface EmprestimosRealizadosDAO {
	
	public void adicionaEmprestimosRealizados(EmprestimosRealizados emprestimosRealizados);
	public List<EmprestimosRealizados> pesquisaEmprestimosRealizados(String dataDe, String dataAte);
	
}
