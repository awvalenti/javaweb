package com.github.awvalenti.javaweb.ingressolento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comprar-Ingresso")
public class ComprarIngressoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long eventoId = Long.parseLong(request.getParameter("evento.id"));
		request.setAttribute("mensagem", "Sucesso no logout!");

		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "");
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Evento WHERE id = ?")) {
			stmt.setLong(1, eventoId);
			try (ResultSet rs = stmt.executeQuery()) {
				rs.next();
				Evento e = new Evento();
				e.setId(rs.getLong("id"));
				e.setNome(rs.getString("nome"));
				e.setDescricao(rs.getString("descricao"));
				request.setAttribute("evento", e);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/comprar-ingresso.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long idUsuarioLogado = (Long) request.getSession().getAttribute("idUsuarioLogado");
		long eventoId = Long.parseLong(request.getParameter("evento.id"));

		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ingresso(dataHora, usuario_id, evento_id) VALUES(?, ?, ?)")) {
			stmt.setDate(1, new Date(new java.util.Date().getTime()));
			stmt.setLong(2, idUsuarioLogado);
			stmt.setLong(3, eventoId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
