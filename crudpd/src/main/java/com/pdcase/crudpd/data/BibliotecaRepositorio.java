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

import com.pdcase.crudpd.model.Biblioteca;

// Persistence Context serve pra definir qual configuração de acesso a banco de dados pra usar
@PersistenceContext(name = "rcb_PU")

public class BibliotecaRepositorio {
	// Gerenciador de acesso ao banco
		@PersistenceContext
		EntityManager em;

		// Métodos para gerenciamento de transação
		@Resource
		UserTransaction ut;

		public Biblioteca findById(int id) {
			return em.find(Biblioteca.class, id);
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

		public void saveOrUpdate(Biblioteca b) {

			try {
				ut.begin();
			} catch (Exception e) {
				throw new EJBException();
			}

			if (b.getId() == 0) {
				em.persist(b);
				em.flush();
			} else {
				em.merge(b);
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

		public List<Biblioteca> getListLivros() {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Biblioteca> criteria = cb.createQuery(Biblioteca.class);
			Root<Biblioteca> b = criteria.from(Biblioteca.class);
			criteria.select(b).orderBy(cb.asc(b.get("nome_livro")));
			return em.createQuery(criteria).getResultList();
		}
}
