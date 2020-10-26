package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.CadastroRepositorio;
import com.pdcase.crudpd.model.Cadastro;

public class CadastroService {
	@Inject
	private Logger log;

	@Inject
<<<<<<< HEAD
	private static CadastroRepositorio pr;

	@Inject
	private Event<Cadastro> CadastroEventSrc;
=======
	private CadastroRepositorio pr;

	@Inject
	private Event<Cadastro> cadastroEventSrc;
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932

	public void register(Cadastro cadastro) {
		log.info("Registering " + cadastro.getCidade());
		pr.saveOrUpdate(cadastro);
<<<<<<< HEAD
		CadastroEventSrc.fire(cadastro);
	}

	public static List<Cadastro> getAllCadastro(){
=======
		cadastroEventSrc.fire(cadastro);
	}

	public List<Cadastro> getAllCadastro(){
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932

		return pr.getListCadastro();
	}
	public Cadastro edit(int id) {
		return pr.findById(id);
	}

	public void delete(int id) {
<<<<<<< HEAD
	Cadastro cadastro = pr.findById(id);
=======
		Cadastro cadastro = pr.findById(id);
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932

		log.info("Apagando " + cadastro.getCidade());

		pr.deleteById(id);

<<<<<<< HEAD
		CadastroEventSrc.fire(cadastro);

	}
}
=======
	    cadastroEventSrc.fire(cadastro);

	}
}
>>>>>>> e6ee1909c9316b1e32a967bb29ccd98f9e9b6932
