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

@WebServlet("/index")
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
		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "")) {
			try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Evento");
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Evento e = new Evento();
					e.setId(rs.getLong("id"));
					e.setNome(rs.getString("nome"));
					e.setDescricao(rs.getString("descricao"));
					eventos.add(e);
				}
				request.setAttribute("eventos", eventos);
			}
			Long idUsuarioLogado = (Long) request.getSession().getAttribute("idUsuarioLogado");
			if (idUsuarioLogado != null) {
				try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(1) FROM Ingresso WHERE usuario_id = ?")) {
					stmt.setLong(1, idUsuarioLogado);
					try (ResultSet rs = stmt.executeQuery()) {
						rs.next();
						request.setAttribute("quantidadeIngressosComprados", rs.getLong(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
