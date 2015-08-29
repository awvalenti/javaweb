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
						{ "Praiotecnico", "Uma explosao sonoro-visual de cores e decibeis" },
						{ "Virtuose Virtual", "Musica da melhor qualidade, tocada pelos antigos membros da Preto & Banda" },
						{ "Iridolofote", "Luminescencia para os olhares mais exigentes" },
				};
				for (int i = 0; i < dados.length; ++i) {
					stmt.setLong(1, i + 1);
					stmt.setString(2, dados[i][0]);
					stmt.setString(3, dados[i][1]);
					stmt.executeUpdate();
				}
			}
			try (PreparedStatement stmt = conn.prepareStatement(""
					+ "CREATE TABLE Usuario("
					+ " id BIGINT PRIMARY KEY,"
					+ " nomeUsuario VARCHAR(20),"
					+ " senha VARCHAR(20)"
					+ ")"
					+ "")) {
				stmt.execute();
			}
			try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuario(id, nomeUsuario, senha) VALUES(?, ?, ?)")) {
				String[][] dados = {
						{ "abner", "senha1" },
						{ "bianca", "senha2" },
						{ "carmelo", "senha3" },
						{ "diana", "senha4" },
				};
				for (int i = 0; i < dados.length; ++i) {
					stmt.setLong(1, i + 1);
					stmt.setString(2, dados[i][0]);
					stmt.setString(3, dados[i][1]);
					stmt.executeUpdate();
				}
			}
			try (PreparedStatement stmt = conn.prepareStatement(""
					+ "CREATE TABLE Ingresso("
					+ " preco DECIMAL(10, 2),"
					+ " dataHora DATETIME,"
					+ " usuario_id BIGINT,"
					+ " evento_id BIGINT,"
					+ " FOREIGN KEY (usuario_id) REFERENCES Usuario(id),"
					+ " FOREIGN KEY (evento_id) REFERENCES Evento(id)"
					+ ")"
					+ "")) {
				stmt.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
