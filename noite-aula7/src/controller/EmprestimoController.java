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
import model.Emprestimos;
import model.AmigosDAOImpl;
import model.EmprestimosDAOImpl;
import model.Usuario;

@WebServlet("/EmprestimoController")
public class EmprestimoController extends HttpServlet {

	private static final long serialVersionUID = -4445421146085039560L;
	private EmprestimosDAOImpl emprestimoController;

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
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			Amigos a = (Amigos) session.getAttribute("AMIGO");

			if(a != null){
			try {
				if (tipo.equals("pegarEmprestado") || tipo.equals("Emprestar")) {
					emprestimo.setIdUsuario(usuario.getIdUsuario());
					emprestimo.setNomeObjeto(request.getParameter("txtNomeObjeto"));
					if (tipo.equals("pegarEmprestado")) {
						emprestimo.setIdAmigoDono(a.getIdAmigo());
						emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
						emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
						emprestimo.setStatus(request.getParameter("txtStatus"));
						emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
						emprestimoController.adicionaEmprestimos(emprestimo);
					} else if (tipo.equals("Emprestar")) {
						emprestimo.setIdAmigoEmprestimo(a.getIdAmigo());
						emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
						emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
						emprestimo.setStatus(request.getParameter("txtStatus"));
						emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
						emprestimoController.adicionaEmprestimos(emprestimo);
					}
					mensagem = "Empr�stimo adicionado com sucesso.";
				}
				if (acao.equals("carregarAmigos")) {
					AmigosDAOImpl amigo = new AmigosDAOImpl();
					List<Amigos> lista = amigo.pesquisarAmigo("", usuario.getIdUsuario());
					// session.setAttribute("LISTA", lista);

					session.setAttribute("LISTAAMIGO", lista);
				}
			} catch (Throwable e) {
				mensagem = "N�o foi poss�vel adicionar o empr�stimo.";
			}
			} else {
				mensagem = "Nenhum amigo selecionado.";
			}
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./emprestimos.jsp");
		} else {
			mensagem = "Fa�a login para registrar o empr�stimo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
