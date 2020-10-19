package com.pdcase.crudpd.service;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.pdcase.crudpd.model.Member;
import com.pdcase.crudpd.model.Pessoa;

public class PessoaRegistration {
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Pessoa> pessoaEventSrc;

    public void register(Pessoa pessoa) throws Exception {
        log.info("Registering " + pessoa.getNome());
        em.persist(pessoa);
        pessoaEventSrc.fire(pessoa);
    }
}
