package com.github.awvalenti.javaweb.exemplo1.ingressolento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "");
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE nomeUsuario = ? AND senha = ?")) {
			stmt.setString(1, request.getParameter("nomeUsuario"));
			stmt.setString(2, request.getParameter("senha"));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("idUsuarioLogado", rs.getLong("id"));
				session.setAttribute("nomeUsuarioLogado", rs.getString("nomeUsuario"));
			} else {
				request.setAttribute("mensagem", "Erro no login: usuario/senha desconhecido(s)");
			}
			request.getRequestDispatcher("index").forward(request, response);;
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

}
