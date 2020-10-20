package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Pessoa;

public class PessoaService {
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

	public List<Pessoa> getAllPessoas(){

		return pr.getListPessoas();
	}
	public Pessoa edit(int id) {
		return pr.findById(id);
	}

	public void delete(int id) {
		Pessoa pessoa = pr.findById(id);

		log.info("Apagando " + pessoa.getNome());

		pr.deleteById(id);

		pessoaEventSrc.fire(pessoa);

	}
}
