package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.pdcase.crudpd.model.Estado;
import com.pdcase.crudpd.service.EstadoService;

public class EstadoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstadoService estadoService;
	
	private transient Logger log;
	
	@Inject
	private Estado newEstado;

	public Estado getNewEstado() {
		return newEstado;
	}

	public void setNewEstado(Estado newEstado) {
		this.newEstado = newEstado;
	}
	
	@PostConstruct
	public void initNewEstado() {
		newEstado = new Estado();
	}
	
	public List<Estado> getAllEstados() {
		return estadoService.getAllEstados();		
	}
	
	public void register() {
		try {
			estadoService.register(newEstado);
			
			initNewEstado();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	public void delete(int id) {
		try {
			estadoService.delete(id);
			
			initNewEstado();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	public void edit(int id) {
		try {
			newEstado = estadoService.edit(id);			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	

}
