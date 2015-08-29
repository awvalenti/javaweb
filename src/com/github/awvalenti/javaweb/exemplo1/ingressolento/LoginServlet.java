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
				request.getSession().setAttribute("usuarioLogado", rs.getString("nomeUsuario"));
				request.setAttribute("mensagem", "Sucesso no login!");
			} else {
				request.setAttribute("mensagem", "Erro no login: usuario/senha desconhecido(s)");
			}
			request.getRequestDispatcher("index").forward(request, response);;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

}
