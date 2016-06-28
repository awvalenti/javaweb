package com.github.awvalenti.javaweb.ingressolento.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import com.github.awvalenti.javaweb.ingressolento.entidades.Ingresso;

@Component
public class RepositorioIngressos {

	private final EntityManager em;

	public RepositorioIngressos(EntityManager em) {
		this.em = em;
	}

	public List<Ingresso> todos() {
		return em.createQuery("FROM " + Ingresso.class.getSimpleName(), Ingresso.class)
				.getResultList();
	}

	public Ingresso comId(long id) {
		return em.find(Ingresso.class, id);
	}

	public void incluir(Ingresso e) {
		em.persist(e);
	}

	public Long quantidadeCompradaPor(Long usuarioId) {
		return em.createQuery(""
				+ "SELECT COUNT(1)"
				+ " FROM " + Ingresso.class.getSimpleName()
				+ " WHERE comprador.id = :usuarioId"
				+ "", Long.class)
				.setParameter("usuarioId", usuarioId)
				.getSingleResult();
	}

}
