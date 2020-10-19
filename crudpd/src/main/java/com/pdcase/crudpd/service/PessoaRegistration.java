package com.pdcase.crudpd.service;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Pessoa;

public class PessoaRegistration {
	@Inject
    private Logger log;

    @Inject
    private PessoaRepositorio pr;

    @Inject
    private Event<Pessoa> pessoaEventSrc;

    public void register(Pessoa pessoa) throws Exception {
        log.info("Registering " + pessoa.getNome());
        pr.saveOrUpdate(pessoa);
        pessoaEventSrc.fire(pessoa);
    }
}
