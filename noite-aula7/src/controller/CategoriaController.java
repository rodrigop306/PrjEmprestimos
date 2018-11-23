package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amigos;
import model.AmigosDAOImpl;
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
			if(acao.equals("voltar")){
				session.setAttribute("LISTA", null);
				response.sendRedirect("./principal.jsp");
			} else if(acao.equals("emprestimos")){
				AmigosDAOImpl amigo = new AmigosDAOImpl();
				List<Amigos> lista = amigo.pesquisarAmigo("", u.getIdUsuario());
				session.setAttribute("LISTAAMIGO", lista);
				CategoriaDAOImpl cat = new CategoriaDAOImpl();
				List<Categoria> listaCat = cat.listaCategoria();
				session.setAttribute("LISTACAT", listaCat);
				response.sendRedirect("./emprestimos.jsp");
			} else if(acao.equals("amigos")){
				response.sendRedirect("./amigos.jsp");
			} else {
				try {
					if (acao.equals("adicionar")) {
						Categoria c = new Categoria();
						String tipo = request.getParameter("txtTipo");
						if(tipo != null && !tipo.trim().equals("")){
							c.setTipo(tipo);
							categoriaDAO.adicionaCategoria(c);
							mensagem = "Categoria adicionada com sucesso";
						} else {
							mensagem = "Informe o nome da categoria para cadastrá-la.";
						}
					}
					if (acao.equals("pesquisar")) {
						List<Categoria> lista = categoriaDAO.pesquisaCategoria(request.getParameter("txtTipo"));
						session.setAttribute("LISTA", lista);
						mensagem = "A busca retornou " + lista.size() + " resultados.";
					}
					if ("editar".equals(acao)) {
						String id = request.getParameter("txtId");
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
						String tipo = (String) request.getParameter("txtTipo");
						if(tipo != null && !tipo.trim().equals("")){
							c.setTipo(tipo);
							categoriaDAO.editarCategoria(c);	
							List<Categoria> lista = categoriaDAO.pesquisaCategoria("");
							session.setAttribute("LISTA", lista);
							mensagem = "Categoria atualizada com sucesso";
						} else {
							mensagem = "Informe o tipo da categoria.";
						}
					}
				} catch (Throwable e) {
					mensagem = "Não foi possível realizar a ação.";
				}
				session.setAttribute("MENSAGEM", mensagem);
				response.sendRedirect("./categoria.jsp");
			}
		} else {
			mensagem = "Faça o login para adicionar categorias.";
			response.sendRedirect("./index.jsp");
		}
	}
}