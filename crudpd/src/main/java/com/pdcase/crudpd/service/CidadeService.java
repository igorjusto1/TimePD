package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.CidadeRepositorio;
import com.pdcase.crudpd.model.Cidade;

public class CidadeService {

	@Inject
	private Logger log;

	@Inject
	private CidadeRepositorio pr;

	@Inject
	private Event<Cidade> estadoEventSrc;

	public void register(Cidade cidade) {
		log.info("Registering " + cidade.getNomeCidade());
		pr.saveOrUpdate(cidade);
		estadoEventSrc.fire(cidade);
	}

	public List<Cidade> getAllCidades() {
		return pr.getListCidades();
	}

	public Cidade edit(int id) {
		return pr.findById(id);
	}

	public void delete(int id) {
		Cidade cidade = pr.findById(id);

		log.info("Apagando " + cidade.getNomeCidade());

		pr.deleteById(id);

		estadoEventSrc.fire(cidade);
	}

}
