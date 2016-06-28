package com.github.awvalenti.javaweb.ingressolento.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import com.github.awvalenti.javaweb.ingressolento.entidades.Evento;

@Component
public class RepositorioEventos {

	private final EntityManager em;

	public RepositorioEventos(EntityManager em) {
		this.em = em;
	}

	public List<Evento> todos() {
		return em.createQuery("FROM " + Evento.class.getSimpleName(), Evento.class).getResultList();
	}

	public Evento comId(long id) {
		return em.find(Evento.class, id);
	}

	public void incluir(Evento e) {
		em.persist(e);
	}

}
