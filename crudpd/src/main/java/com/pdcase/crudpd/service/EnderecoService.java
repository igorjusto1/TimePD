package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.EnderecoRepositorio;
import com.pdcase.crudpd.model.Endereco;
import com.pdcase.crudpd.viewmodel.EnderecoSelectList;

public class EnderecoService {
	@Inject
	private Logger log;

	@Inject
	private EnderecoRepositorio pr;

	@Inject
	private Event<Endereco> enderecoEventSrc;

	public void register(Endereco endereco) {
		log.info("Registering " + endereco.getCidade());
		pr.saveOrUpdate(endereco);
		enderecoEventSrc.fire(endereco);
	}

	public List<Endereco> getAllEndereco() {

		return pr.getListEndereco();
	}

	public Endereco edit(int id) {
		return pr.findById(id);
	}

	public EnderecoSelectList find(int id) {
		return new EnderecoSelectList(pr.findById(id));
	}

	public void delete(int id) {
		Endereco endereco = pr.findById(id);

		log.info("Apagando " + endereco.getCidade());

		pr.deleteById(id);

		enderecoEventSrc.fire(endereco);

	}
}
