package com.pdcase.crudpd.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.model.Estado;
import com.pdcase.crudpd.service.EstadoService;

@Named
@RequestScoped
public class EstadoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoService estadoService;

	@Inject
	private Logger log;

	@Inject
	private Estado newEstado;

	public Estado getNewEstado() {
		return newEstado;
	}

	public void setNewEstado(Estado newEstado) {
		this.newEstado = newEstado;
	}
	
	public String getNomeEstado() {
		return this.newEstado.getNomeEstado();
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
