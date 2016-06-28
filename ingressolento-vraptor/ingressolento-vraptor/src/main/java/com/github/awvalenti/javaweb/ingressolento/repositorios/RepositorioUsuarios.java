package com.github.awvalenti.javaweb.ingressolento.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.mindrot.jbcrypt.BCrypt;

import br.com.caelum.vraptor.ioc.Component;

import com.github.awvalenti.javaweb.ingressolento.entidades.Usuario;

@Component
public class RepositorioUsuarios {

	private final EntityManager em;

	public RepositorioUsuarios(EntityManager em) {
		this.em = em;
	}

	public void incluir(Usuario u) {
		em.persist(u);
	}

	public Usuario comId(long id) {
		return em.find(Usuario.class, id);
	}

	public Long idDoUsuario(String nomeUsuario, String senhaEmClaro) {
		List<Usuario> usuarios = em.createQuery(""
				+ "FROM " + Usuario.class.getSimpleName()
				+ " WHERE nomeUsuario = :nomeUsuario"
				+ "", Usuario.class)
				.setParameter("nomeUsuario", nomeUsuario)
				.getResultList();

		if (usuarios.isEmpty()) return null;

		Usuario usuario = usuarios.get(0);

		boolean senhaConfere = BCrypt.checkpw(senhaEmClaro, usuario.getSenhaBcrypt());

		return senhaConfere ? usuario.getId() : null;
	}

}
