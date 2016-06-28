package com.github.awvalenti.javaweb.ingressolento.banco;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

@RequestScoped
@Component
public class EntityManagerComponentFactory implements ComponentFactory<EntityManager> {

	private final EntityManager em;

	public EntityManagerComponentFactory(EntityManagerFactory emf) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@Override
	public EntityManager getInstance() {
		return em;
	}

	@PreDestroy
	public void fechar() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.commit();

		} catch (Throwable t) {
			if (tx.isActive()) tx.rollback();

		} finally {
			em.close();
		}
	}

}
