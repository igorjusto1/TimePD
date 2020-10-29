package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.CidadeRepositorio;
import com.pdcase.crudpd.data.EstadoRepositorio;
import com.pdcase.crudpd.model.Cidade;
import com.pdcase.crudpd.model.Estado;
import com.pdcase.crudpd.viewmodel.CidadeViewModel;
import com.pdcase.crudpd.viewmodel.EstadoSelectList;

public class CidadeService {

	@Inject
	private Logger log;

	@Inject
	private CidadeRepositorio cr;

	@Inject
	private EstadoRepositorio er;

	@Inject
	private Event<Cidade> estadoEventSrc;

	public void register(CidadeViewModel cidade) {
		log.info("Registering " + cidade.getNomeCidade());
		cr.saveOrUpdate(cidade.toCidade());
	}

	public List<Cidade> getAllCidades() {
		return cr.getListCidades();
	}

	public List<CidadeViewModel> getAllCidadesViewModel() {
		return cr.getListCidades().stream().map(CidadeViewModel::new).collect(Collectors.toList());
	}

	public List<EstadoSelectList> getAllEstados() {
		return er.getListEstados().stream().map(EstadoSelectList::new).collect(Collectors.toList());
	}

	public CidadeViewModel edit(int id) {
		return new CidadeViewModel(cr.findById(id));
	}

	public void delete(int id) {
		Cidade cidade = cr.findById(id);

		log.info("Apagando " + cidade.getNomeCidade());

		cr.deleteById(id);

		estadoEventSrc.fire(cidade);
	}

}
