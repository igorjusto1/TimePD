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

import com.pdcase.crudpd.model.Cadastro;

// Persistence Context serve pra definir qual configuração de acesso a banco de dados pra usar
@PersistenceContext(name = "rcb_PU")
public class CadastroRepositorio {
	
	// Gerenciador de acesso ao banco
	@PersistenceContext
	EntityManager em;

	// Métodos para gerenciamento de transação
	@Resource
	UserTransaction ut;

	public Cadastro findById(int id) {
		return em.find(Cadastro.class, id);
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

	public void saveOrUpdate(Cadastro p) {

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

	public List<Cadastro> getListCadastro() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cadastro> criteria = cb.createQuery(Cadastro.class);
		Root<Cadastro> p = criteria.from(Cadastro.class);
		criteria.select(p).orderBy(cb.asc(p.get("cidade")));
		return em.createQuery(criteria).getResultList();
	}
}