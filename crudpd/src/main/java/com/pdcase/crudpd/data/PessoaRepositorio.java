package com.pdcase.crudpd.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pdcase.crudpd.model.Pessoa;

@PersistenceContext(name = "rcb_PU")
public class PessoaRepositorio {
	@Inject
	private EntityManager em;

	public Pessoa findById(Long id) {
		return em.find(Pessoa.class, id);
	}

	public void deleteById(Long id) {
		try {
			em.getTransaction().begin();
			em.remove(findById(id));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

	}

	public void update(Pessoa p) {
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	public List<Pessoa> getListPessoas() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = cb.createQuery(Pessoa.class);
		Root<Pessoa> p = criteria.from(Pessoa.class);		
		criteria.select(p).orderBy(cb.asc(p.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}
