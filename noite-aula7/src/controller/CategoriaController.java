package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categoria;
import model.CategoriaDAOImpl;
import model.Usuario;

@WebServlet("/CategoriaController")
public class CategoriaController extends HttpServlet {

	private static final long serialVersionUID = -4445421146085039560L;
	private CategoriaDAOImpl categoriaDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("cmd");
		String mensagem = "";
		HttpSession session = request.getSession();
		categoriaDAO = new CategoriaDAOImpl();
		Usuario u = (Usuario) session.getAttribute("USUARIO");
		if (u != null) {
			try {
				if (acao.equals("adicionar")) {
					Categoria c = new Categoria();
					c.setTipo(request.getParameter("txtTipo"));
					categoriaDAO.adicionaCategoria(c);
					mensagem = "Categoria adicionada com sucesso";
				}
				if (acao.equals("pesquisar")) {
					List<Categoria> lista = categoriaDAO.pesquisaCategoria(request.getParameter("txtTipo"));
					session.setAttribute("LISTA", lista);
					mensagem = "A busca retornou " + lista.size() + " resultados.";
				}
				if ("editar".equals(acao)) {
					String id = request.getParameter("txtId");
					// Categoria s = sDao.pesquisarPorId(Long.parseLong(id));
					Categoria c = categoriaDAO.getCategoria(Integer.parseInt(id));
					session.setAttribute("CATEGORIA_ATUAL", c);
					mensagem = "Detalhes da categoria com o Id " + id;
				}
				if ("remover".equals(acao)) {
					String idCat = request.getParameter("txtId");
					categoriaDAO.removeCategoria(Integer.parseInt(idCat));
					mensagem = "Categoria com o Id " + idCat + " foi removido";
					List<Categoria> lista = categoriaDAO.pesquisaCategoria("");
					session.setAttribute("LISTA", lista);
				} else if ("salvar".equals(acao)) {
					Categoria c = new Categoria();
					c.setId(Integer.parseInt(request.getParameter("txtId")));
					c.setTipo(request.getParameter("txtTipo"));
					categoriaDAO.editarCategoria(c);
					mensagem = "Categoria atualizada com sucesso";
					List<Categoria> lista = categoriaDAO.pesquisaCategoria("");
					session.setAttribute("LISTA", lista);
					mensagem = "Sorvete foi atualizado com sucesso";
					
					/*Sorvete s = new Sorvete();
					String id = request.getParameter("txtId");
					s.setSabor(request.getParameter("txtSabor"));
					s.setPreco(Float.parseFloat(request.getParameter("txtPreco")));
					s.setTipo(request.getParameter("txtTipo"));
					s.setCobertura(request.getParameter("txtCobertura"));
					s.setImagem(request.getParameter("txtImagem"));
					sDao.salvar( Long.parseLong(id), s );
					List<Sorvete> lista = sDao.presquisarPorSabor("");
					session.setAttribute("LISTA", lista);				
					msg = "Sorvete foi atualizado com sucesso";*/
				}
			} catch (Throwable e) {
				mensagem = "Não foi possível realizar a ação.";
			}
		} else {
			mensagem = "Faça o login para adicionar categorias.";
			response.sendRedirect("./index.jsp");
		}
		session.setAttribute("MENSAGEM", mensagem);
		response.sendRedirect("./categoria.jsp");
	}
}
