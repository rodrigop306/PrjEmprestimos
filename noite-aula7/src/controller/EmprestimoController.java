package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoriaDAOImpl;
import model.Amigos;
import model.Emprestimos;
import model.AmigosDAOImpl;
import model.Categoria;
import model.EmprestimosDAOImpl;
import model.Usuario;
import model.AmigosDAOImpl;

@WebServlet("/EmprestimoController")
public class EmprestimoController extends HttpServlet {

	private static final long serialVersionUID = -4445421146085039560L;
	private EmprestimosDAOImpl emprestimoController;
	private CategoriaDAOImpl catDAO;
	private AmigosDAOImpl amigoDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("txtAcao");
		String acao = request.getParameter("cmd");

		String mensagem = "";
		Emprestimos emprestimo = new Emprestimos();
		emprestimoController = new EmprestimosDAOImpl();
		catDAO = new CategoriaDAOImpl();
		amigoDAO = new AmigosDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			//Amigos a = new Amigos();

			// if(a != null){
			try {
				if(acao.equals("amigos")){
					response.sendRedirect("./amigos.jsp");
				}
				if(acao.equals("categorias")){
					response.sendRedirect("./categoria.jsp");
				}
				if (acao.equals("adicionar")) {
					//if (tipo.equals("pegarEmprestado") || tipo.equals("Emprestar")) {
						emprestimo.setIdUsuario(usuario.getIdUsuario());
						emprestimo.setNomeObjeto(request.getParameter("txtNomeObjeto"));
						int idCategoria = Integer.parseInt(request.getParameter("txtCategoria"));
						emprestimo.setIdCategoria(idCategoria);
						emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
						emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
						emprestimo.setStatus(request.getParameter("txtStatus"));
						emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
						//emprestimoController.adicionaEmprestimos(emprestimo);
						if (tipo.equals("pegarEmprestado")) {
							int idAmigo = Integer.parseInt(request.getParameter("amigo"));
							emprestimo.setIdAmigoDono(idAmigo);
						} else if (tipo.equals("emprestar")) {
							int idAmigo = Integer.parseInt(request.getParameter("amigo"));
							System.out.println(request.getParameter("amigo"));
							emprestimo.setIdAmigoEmprestimo(idAmigo);
						}
						emprestimoController.adicionaEmprestimos(emprestimo);
						mensagem = "Empréstimo adicionado com sucesso.";
						
					//}
				}else if (acao.equals("pesquisar")) {
					emprestimo.setIdUsuario(usuario.getIdUsuario());
					emprestimo.setNomeObjeto(request.getParameter("txtNomeObjeto"));
					int idCategoria = Integer.parseInt(request.getParameter("txtCategoria"));
					emprestimo.setIdCategoria(idCategoria);
					emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
					emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
					emprestimo.setStatus(request.getParameter("txtStatus"));
					emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
					if (tipo.equals("pegarEmprestado")) {
						int idAmigo = Integer.parseInt(request.getParameter("amigo"));
						emprestimo.setIdAmigoDono(idAmigo);
					} else if (tipo.equals("emprestar")) {
						int idAmigo = Integer.parseInt(request.getParameter("amigo"));
						System.out.println(request.getParameter("amigo"));
						emprestimo.setIdAmigoEmprestimo(idAmigo);
					}
					List<Emprestimos> emp = emprestimoController.pesquisaEmprestimos(emprestimo, usuario.getIdUsuario());
					session.setAttribute("LISTA", emp);
				}else if ("remover".equals(acao)) {
					String idEmp = request.getParameter("txtId");
					emprestimoController.removeEmprestimo(Integer.parseInt(idEmp));
					mensagem = "Categoria com o Id " + idEmp + " foi removido";
					//List<Categoria> lista = categoriaDAO.pesquisaCategoria("");
					//session.setAttribute("LISTA", lista);
				}
			} catch (Throwable e) {
				mensagem = "Não foi possível adicionar o empréstimo.";
				System.out.println(e);
			}
//			} else {
//				mensagem = "Nenhum amigo selecionado.";
//			}
			session.setAttribute("MENSAGEM", mensagem);
			AmigosDAOImpl amigo = new AmigosDAOImpl();
			List<Amigos> lista = amigo.pesquisarAmigo("", usuario.getIdUsuario());
			// session.setAttribute("LISTA", lista);

			session.setAttribute("LISTAAMIGO", lista);
			response.sendRedirect("./emprestimos.jsp");
		} else {
			mensagem = "Faça login para registrar o empréstimo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
