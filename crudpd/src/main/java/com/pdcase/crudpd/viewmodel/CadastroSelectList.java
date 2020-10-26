package com.pdcase.crudpd.viewmodel;

import com.pdcase.crudpd.model.Cadastro;

public class CadastroSelectList {
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

	public CadastroSelectList() {
		// default
	}

	public CadastroSelectList(Cadastro c) {
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
	

    public CadastroSelectList(int id) {
		this.id = id;
	}

	@Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass())
            ? this.id == (((CadastroSelectList) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
       return this.id;
    }

	public Cadastro toEndereco() {
		Cadastro c = new Cadastro();
		c.setId(this.id);

		return c;
	}
}
