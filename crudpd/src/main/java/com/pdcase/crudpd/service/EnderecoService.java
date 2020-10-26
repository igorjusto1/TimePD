package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.EnderecoRepositorio;
import com.pdcase.crudpd.model.Endereco;
import com.pdcase.crudpd.viewmodel.CadastroSelectList;

public class EnderecoService {
	@Inject
	private Logger log;

	@Inject
	private EnderecoRepositorio pr;

	@Inject
	private Event<Endereco> cadastroEventSrc;

	public void register(Endereco cadastro) {
		log.info("Registering " + cadastro.getCidade());
		pr.saveOrUpdate(cadastro);
		cadastroEventSrc.fire(cadastro);
	}

	public List<Endereco> getAllCadastro() {

		return pr.getListCadastro();
	}

	public Endereco edit(int id) {
		return pr.findById(id);
	}

	public CadastroSelectList find(int id) {
		return new CadastroSelectList(pr.findById(id));
	}

	public void delete(int id) {
		Endereco cadastro = pr.findById(id);

		log.info("Apagando " + cadastro.getCidade());

		pr.deleteById(id);

		cadastroEventSrc.fire(cadastro);

	}
}
