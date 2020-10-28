package com.pdcase.crudpd.viewmodel;

import java.io.Serializable;

import com.pdcase.crudpd.model.Estado;

public class EstadoSelectList implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String displayName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public EstadoSelectList() {
		// Default Constructor
	}

	public EstadoSelectList(Estado e) {
		if (e != null) {
			this.id = e.getIdEstado();
			this.displayName = e.getNomeEstado();
		} else {
			this.id = 0;
			this.displayName = null;
		}

	}

	public EstadoSelectList(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass()) ? this.id == (((EstadoSelectList) other).id)
				: (other == this);
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	public Estado toEstado() {
		Estado e = new Estado();
		e.setIdEstado(this.id);
		
		return e;
	}

}
