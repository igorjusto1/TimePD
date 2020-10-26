package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.CadastroRepositorio;
import com.pdcase.crudpd.model.Cadastro;
import com.pdcase.crudpd.viewmodel.CadastroSelectList;

public class CadastroService {
	@Inject
	private Logger log;

	@Inject
	private CadastroRepositorio pr;

	@Inject
	private Event<Cadastro> cadastroEventSrc;

	public void register(Cadastro cadastro) {
		log.info("Registering " + cadastro.getCidade());
		pr.saveOrUpdate(cadastro);
		cadastroEventSrc.fire(cadastro);
	}

	public List<Cadastro> getAllCadastro() {

		return pr.getListCadastro();
	}

	public Cadastro edit(int id) {
		return pr.findById(id);
	}

	public CadastroSelectList find(int id) {
		return new CadastroSelectList(pr.findById(id));
	}

	public void delete(int id) {
		Cadastro cadastro = pr.findById(id);

		log.info("Apagando " + cadastro.getCidade());

		pr.deleteById(id);

		cadastroEventSrc.fire(cadastro);

	}
}
