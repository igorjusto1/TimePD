package com.pdcase.crudpd.viewmodel;

import java.io.Serializable;

import com.pdcase.crudpd.model.Endereco;

public class EnderecoSelectList implements Serializable {
	/**
	 * 
	 */
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

	public EnderecoSelectList() {
		// default
	}

	public EnderecoSelectList(Endereco c) {
		if (c != null) {
			this.id = c.getId();
			this.displayName = c.toString();

		}
		else
		{
			this.id = 0;
			this.displayName = null;
		}
	}
	

    public EnderecoSelectList(int id) {
		this.id = id;
	}

	@Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass())
            ? this.id == (((EnderecoSelectList) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
       return this.id;
    }

	public Endereco toEndereco() {
		Endereco c = new Endereco();
		c.setId(this.id);

		return c;
	}
}
