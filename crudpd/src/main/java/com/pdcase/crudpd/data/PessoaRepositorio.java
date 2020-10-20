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

import com.pdcase.crudpd.model.Pessoa;

@PersistenceContext(name = "rcb_PU")
public class PessoaRepositorio {
	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	public Pessoa findById(Long id) {
		return em.find(Pessoa.class, id);
	}

	public void deleteById(Long id) {
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

	public void saveOrUpdate(Pessoa p) {

		try {
			ut.begin();
		} catch (Exception e) {
			throw new EJBException();
		}

		if (p.getId() == 0) {
			em.persist(p);
			em.flush();
		} else {
			em.merge(p);
			em.flush();
		}

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

	public List<Pessoa> getListPessoas() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = cb.createQuery(Pessoa.class);
		Root<Pessoa> p = criteria.from(Pessoa.class);
		criteria.select(p).orderBy(cb.asc(p.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}
