package com.pdcase.crudpd.data;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import com.pdcase.crudpd.model.Cidade;

@PersistenceContext(name = "rcb_PU")

public class CidadeRepositorio {
	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	public Cidade findById(int id) {
		return em.find(Cidade.class, id);
	}

	public void deleteById(int id) {
		try {
			ut.begin();
		} catch (Exception e) {
			throw new EJBException();
		}

		em.remove(findById(id));

		try {
			ut.commit();
		} catch (Exception e) {
			try {
				this.ut.rollback();
			} catch (Exception e1) {
				throw new EJBException();

			}
		}
	}

	public void saveOrUpdate(Cidade c) {
		try {
			ut.begin();
		} catch (Exception e) {
			throw new EJBException();
		}

		if (c.getIdCidade() == 0) {
			em.persist(c);
			em.flush();
		} else {
			em.merge(c);
			em.flush();
		}

		try {
			ut.commit();
		} catch (Exception e) {
			try {
				this.ut.rollback();
			} catch (Exception e1) {
				throw new EJBException();
			}
		}
	}

	public List<Cidade> getListCidades() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = cb.createQuery(Cidade.class);
		Root<Cidade> c = criteria.from(Cidade.class);
		criteria.select(c).orderBy(cb.asc(c.get("nomeCidade")));
		
		return em.createQuery(criteria).getResultList();
	}

}
