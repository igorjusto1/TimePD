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

import com.pdcase.crudpd.model.Estado;

@PersistenceContext(name = "rcb_PU")

public class EstadoRepositorio {
	@PersistenceContext(name = "rcb_PU")
	EntityManager em;

	@Resource
	UserTransaction ut;

	public Estado findById(int id) {
		return em.find(Estado.class, id);
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
				throw new EJBException(e1);
			}
		}

	}

	public void saveOrUpdate(Estado e) {
		try {
			ut.begin();
		} catch (Exception ex) {
			throw new EJBException();
		}

		if (e.getIdEstado() == 0) {
			em.persist(e);
			em.flush();
		} else {
			em.merge(e);
			em.flush();
		}

		try {
			ut.commit();
		} catch (Exception ex) {
			try {
				this.ut.rollback();
			} catch (Exception e1) {
				throw new EJBException(e1);
			}
		}
	}

	public List<Estado> getListEstados() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estado> criteria = cb.createQuery(Estado.class);
		Root<Estado> e = criteria.from(Estado.class);
		criteria.select(e).orderBy(cb.asc(e.get("nomeEstado")));

		return em.createQuery(criteria).getResultList();
	}

}
