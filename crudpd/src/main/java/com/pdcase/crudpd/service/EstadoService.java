package com.pdcase.crudpd.service;


import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.pdcase.crudpd.data.EstadoRepositorio;
import com.pdcase.crudpd.model.Estado;

public class EstadoService {
	
	@Inject
	private Logger log;
	
	@Inject
	private EstadoRepositorio pr;
	
	@Inject
	private Event<Estado> estadoEventSrc;
	
	public void register(Estado estado) {
		log.info("Registering " + estado.getNomeEstado());
		pr.saveOrUpdate(estado);
		estadoEventSrc.fire(estado);
	}
	
	public List<Estado> getAllEstados() {
		return pr.getListEstados();
	}
	
	public Estado edit(int id) {
		return pr.findById(id);
	}
	
	public void delete(int id) {
		Estado estado = pr.findById(id);
		
		log.info("Apagando " + estado.getNomeEstado());
		
		pr.deleteById(id);
		
		estadoEventSrc.fire(estado);
	}
	

}
