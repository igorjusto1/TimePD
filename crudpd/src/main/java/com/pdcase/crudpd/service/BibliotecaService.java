package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.BibliotecaRepositorio;
import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Biblioteca;
import com.pdcase.crudpd.model.Pessoa;

public class BibliotecaService {
	@Inject
	private Logger log;

	@Inject
	private BibliotecaRepositorio pr;

	@Inject
	private Event<Biblioteca> bibliotecaEventSrc;

	public void register(Biblioteca livro) {
		log.info("Registering " + livro.getNome_livro());
		pr.saveOrUpdate(livro);
		bibliotecaEventSrc.fire(livro);
	}

	public List<Biblioteca> getAllLivros(){

		return pr.getListLivros();
	}
	public Biblioteca edit(int id) {
		return pr.findById(id);
	}

	public void delete(int id) {
		Biblioteca livro = pr.findById(id);

		log.info("Apagando " + livro.getNome_livro());

		pr.deleteById(id);

		bibliotecaEventSrc.fire(livro);

	}
}
