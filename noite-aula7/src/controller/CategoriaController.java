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
		if(u != null){
			try {
			if(acao.equals("adicionar")){
				Categoria c = new Categoria();
				c.setTipo(request.getParameter("txtTipo"));
				categoriaDAO.adicionaCategoria(c);
				mensagem = "Categoria adicionada com sucesso";
			} else if(acao.equals("pesquisar")){
				List<Categoria> lista = categoriaDAO.pesquisaCategoria(request.getParameter("txtTipo"));
				session.setAttribute("LISTA", lista);
				mensagem = "A busca retornou "+lista.size()+" resultados.";
			}
			} catch(Throwable e){
				mensagem = "N�o foi poss�vel realizar a a��o.";
			}
		} else {
			mensagem = "Fa�a o login para adicionar categorias.";
			response.sendRedirect("./index.jsp");
		}
		session.setAttribute("MENSAGEM", mensagem);
		response.sendRedirect("./categoria.jsp");
	}
}