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
import model.Emprestimos;
import model.EmprestimosDAOImpl;
import model.Usuario;

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
			if (acao.equals("voltar")) {
				session.setAttribute("LISTA", null);
				response.sendRedirect("./principal.jsp");
			} else if (acao.equals("amigos")) {
				response.sendRedirect("./amigos.jsp");
			} else if (acao.equals("categorias")) {
				response.sendRedirect("./categoria.jsp");
			} else {
				try {
					if (acao.equals("adicionar")) {
						String nomeObjeto = (String) request.getParameter("txtNomeObjeto");
						int idCategoria = Integer.parseInt(request.getParameter("txtCategoria"));
						int idAmigo = Integer.parseInt(request.getParameter("amigo"));
						String dataEmprestimo = (String) request.getParameter("txtDataEmprestimo");
						String dataDevolucao = (String) request.getParameter("txtDataDevolucao");
						if((nomeObjeto != null && !nomeObjeto.trim().equals(""))
								&& idCategoria != 0
								&& idAmigo != 0
								&& dataEmprestimo != null && !dataEmprestimo.trim().equals("")
								&& dataDevolucao != null && !dataDevolucao.trim().equals("")){
							emprestimo.setIdUsuario(usuario.getIdUsuario());
							emprestimo.setNomeObjeto(nomeObjeto);
							emprestimo.setIdCategoria(idCategoria);
							emprestimo.setDataEmprestimo(dataEmprestimo);
							emprestimo.setDataDevolucao(dataDevolucao);
							emprestimo.setStatus(request.getParameter("txtStatus"));
							emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
							if (tipo.equals("pegarEmprestado")) {
								emprestimo.setIdAmigoDono(idAmigo);
							} else if (tipo.equals("emprestar")) {
								emprestimo.setIdAmigoEmprestimo(idAmigo);
							}
							emprestimoController.adicionaEmprestimos(emprestimo);
							mensagem = "Empréstimo adicionado com sucesso.";
						} else {
							mensagem = "Informe todos os campos obrigatórios, marcados com *.";
						}
					} else if (acao.equals("pesquisar")) {
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
						List<Emprestimos> emp = emprestimoController.pesquisaEmprestimos(emprestimo,
								usuario.getIdUsuario());
						session.setAttribute("LISTA", emp);
						mensagem = "Foram encontrados " + emp.size() + " empréstimos";
					} else if ("remover".equals(acao)) {
						String idEmp = request.getParameter("txtId");
						emprestimoController.removeEmprestimo(Integer.parseInt(idEmp));
						mensagem = "Empréstimo com o Id " + idEmp + " foi removido";
					} else if ("editar".equals(acao)) {
						String id = request.getParameter("txtId");
						emprestimo = emprestimoController.pesquisaPorId(Integer.parseInt(id));
						session.setAttribute("EMP_ATUAL", emprestimo);
						mensagem = "Detalhes da empréstimos com o Id " + id;
					} else if ("salvar".equals(acao)) {
						emprestimo = new Emprestimos();
						String nomeObjeto = (String) request.getParameter("txtNomeObjeto");
						int idCategoria = Integer.parseInt(request.getParameter("txtCategoria"));
						int idAmigo = Integer.parseInt(request.getParameter("amigo"));
						String dataEmprestimo = (String) request.getParameter("txtDataEmprestimo");
						String dataDevolucao = (String) request.getParameter("txtDataDevolucao");
						if((nomeObjeto != null && !nomeObjeto.trim().equals(""))
								&& idCategoria != 0
								&& idAmigo != 0
								&& (dataEmprestimo != null && !dataEmprestimo.trim().equals(""))
								&& dataDevolucao != null && !dataDevolucao.trim().equals("")){
							emprestimo.setIdUsuario(usuario.getIdUsuario());
							emprestimo.setIdEmprestimos(Integer.parseInt(request.getParameter("txtId")));
							emprestimo.setNomeObjeto(nomeObjeto);
							emprestimo.setIdCategoria(idCategoria);
							emprestimo.setDataEmprestimo(dataEmprestimo);
							emprestimo.setDataDevolucao(dataDevolucao);
							emprestimo.setStatus(request.getParameter("txtStatus"));
							emprestimo.setDetalhesEmprestimo(request.getParameter("txtDetalhes"));
							if (tipo.equals("pegarEmprestado")) {
								emprestimo.setIdAmigoDono(idAmigo);
							} else if (tipo.equals("emprestar")) {
								emprestimo.setIdAmigoEmprestimo(idAmigo);
							}
							emprestimoController.atualizaEmprestimo(emprestimo);
							mensagem = "Emprestimo atualizado com sucesso";
						} else {
							mensagem = "Informe os dados obrigatórios (marcados com *) para atualizar o empréstimo";
						}
					}
				} catch (Throwable e) {
					mensagem = "A ação não pôde ser concluída";
					System.out.println(e);
				}
				session.setAttribute("MENSAGEM", mensagem);
				AmigosDAOImpl amigo = new AmigosDAOImpl();
				List<Amigos> lista = amigo.pesquisarAmigo("", usuario.getIdUsuario());
				session.setAttribute("LISTAAMIGO", lista);
				CategoriaDAOImpl cat = new CategoriaDAOImpl();
				List<Categoria> listaCat = cat.listaCategoria();
				session.setAttribute("LISTACAT", listaCat);
				response.sendRedirect("./emprestimos.jsp");
			}
		} else {
			mensagem = "Faça login para registrar o empréstimo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
