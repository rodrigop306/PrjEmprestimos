package dao;

import java.util.List;

import model.Sorvete;

public interface SorveteDAO {
	public void adicionar(Sorvete s) throws GenericDAOException;
	public List<Sorvete> presquisarPorSabor(String sabor) throws GenericDAOException;
	public void remover(long id) throws GenericDAOException;
}
