package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amigos;
import model.Emprestimos;
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
		String acao = request.getParameter("txtAcao");
		String mensagem = "";
		Emprestimos emprestimo = new Emprestimos();
		emprestimoController = new EmprestimosDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if(usuario != null){
			Amigos a = (Amigos) session.getAttribute("AMIGO");
			if(a != null){
				try {
					if(acao.equals("pegarEmprestado") || acao.equals("Emprestar")){
						emprestimo.setIdUsuario(usuario.getIdUsuario()); 
						emprestimo.setNomeObjeto(request.getParameter("txtNomeObjeto"));
						if(acao.equals("pegarEmprestado")){
							emprestimo.setIdAmigoDono(a.getIdAmigo());
							emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
							emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
							emprestimo.setStatus(request.getParameter("txtStatus"));
							emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
							emprestimoController.adicionaEmprestimosRecebidos(emprestimo);
						} else if(acao.equals("Emprestar")){
							emprestimo.setIdAmigoEmprestimo(a.getIdAmigo());
							emprestimo.setDataEmprestimo(request.getParameter("txtDataEmprestimo"));
							emprestimo.setDataDevolucao(request.getParameter("txtDataDevolucao"));
							emprestimo.setStatus(request.getParameter("txtStatus"));
							emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
							emprestimoController.adicionaEmprestimosRealizados(emprestimo);
						}
						mensagem = "Empréstimo adicionado com sucesso.";
					}
				} catch(Throwable e){
					mensagem = "Não foi possível adicionar o empréstimo.";
				}
			} else {
				mensagem = "Nenhum amigo selecionado.";
				session.setAttribute("MENSAGEM", mensagem);
				response.sendRedirect("./emprestimos.jsp");
			}
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./emprestimos.jsp");
		} else {
			mensagem = "Faça login para registrar o empréstimo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
