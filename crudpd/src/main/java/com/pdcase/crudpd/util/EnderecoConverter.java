package com.pdcase.crudpd.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.pdcase.crudpd.viewmodel.EnderecoSelectList;

@FacesConverter("enderecoConverter")
public class EnderecoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String endereco) {
		if (endereco == null || endereco.isEmpty()) {
			return null;
		}

		try {
			return new EnderecoSelectList(Integer.parseInt(endereco));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s não é um ID de Endereço válido", endereco)),
					e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof EnderecoSelectList) {
			return String.valueOf(((EnderecoSelectList) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue + " não é um endereço"));
		}
	}
}
