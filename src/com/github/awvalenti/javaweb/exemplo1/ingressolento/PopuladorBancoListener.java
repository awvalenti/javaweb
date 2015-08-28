package com.github.awvalenti.javaweb.exemplo1.ingressolento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hsqldb.jdbc.JDBCDriver;

@WebListener
public class PopuladorBancoListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			Class.forName(JDBCDriver.class.getName());
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}

		try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:banco", "sa", "")) {
			try (PreparedStatement stmt = conn.prepareStatement(""
					+ "CREATE TABLE Evento("
					+ " id BIGINT PRIMARY KEY,"
					+ " nome VARCHAR(255),"
					+ " descricao VARCHAR(255)"
					+ ")"
					+ "")) {
				stmt.execute();
			}
			try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Evento(id, nome, descricao) VALUES(?, ?, ?)")) {
				String[][] dados = {
						{ "Evento 1", "Um evento fenomenal, com participacao dos mais celebres artistas" },
						{ "Evento 2", "Um evento fenomenal, com participacao dos mais celebres artistas" },
						{ "Evento 3", "Um evento fenomenal, com participacao dos mais celebres artistas" },
				};
				for (int id = 0; id < dados.length; ++id) {
					stmt.setLong(1, id);
					stmt.setString(2, dados[id][0]);
					stmt.setString(3, dados[id][1]);
					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
