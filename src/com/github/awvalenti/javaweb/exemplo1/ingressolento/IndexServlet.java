package com.github.awvalenti.javaweb.exemplo1.ingressolento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("titulo", "Ingresso Lento");
		request.setAttribute("subtitulo",
				"Os melhores ingressos do mundo para os melhores eventos do mundo!");
		request.setAttribute("login", "Login");
		request.setAttribute("nomeUsuario", "Nome de usuario");
		request.setAttribute("senha", "Senha");
		request.setAttribute("entrar", "Entrar");
		request.setAttribute("eventostxt", "Eventos");

		List<Evento> eventos = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "");
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Evento");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Evento e = new Evento();
				e.setId(rs.getLong("id"));
				e.setNome(rs.getString("nome"));
				e.setDescricao(rs.getString("descricao"));
				eventos.add(e);
			}
			request.setAttribute("eventos", eventos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
