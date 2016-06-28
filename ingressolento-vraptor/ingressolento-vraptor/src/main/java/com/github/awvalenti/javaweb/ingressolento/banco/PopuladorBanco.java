package com.github.awvalenti.javaweb.ingressolento.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.mindrot.jbcrypt.BCrypt;

import com.github.awvalenti.javaweb.ingressolento.entidades.Evento;
import com.github.awvalenti.javaweb.ingressolento.entidades.Usuario;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioEventos;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioUsuarios;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@ApplicationScoped
@Component
public class PopuladorBanco {

	public PopuladorBanco(EntityManagerFactory emf) {

		Evento[] eventos = new Evento[] {
				new Evento("Praiotecnico", "Uma explosao sonoro-visual de cores e decibeis"),
				new Evento("Virtuose Virtual",
						"Musica da melhor qualidade, tocada pelos antigos membros da Preto & Banda"),
				new Evento("Iridolofote", "Luminescencia para os olhares mais exigentes") };

		Usuario[] usuarios = new Usuario[] {
				new Usuario("abner", BCrypt.hashpw("senha1", BCrypt.gensalt())),
				new Usuario("bianca", BCrypt.hashpw("senha2", BCrypt.gensalt())),
				new Usuario("carmelo", BCrypt.hashpw("senha3", BCrypt.gensalt())),
				new Usuario("diana", BCrypt.hashpw("senha4", BCrypt.gensalt())) };

		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();

			RepositorioEventos repoEventos = new RepositorioEventos(em);
			for (Evento e : eventos) {
				repoEventos.incluir(e);
			}

			RepositorioUsuarios repoUsuarios = new RepositorioUsuarios(em);
			for (Usuario u : usuarios) {
				repoUsuarios.incluir(u);
			}

			em.getTransaction().commit();

		} finally {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

}
