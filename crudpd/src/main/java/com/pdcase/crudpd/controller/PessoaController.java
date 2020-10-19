package com.pdcase.crudpd.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.PessoaRegistration;

public class PessoaController {

	 @Inject
	    private FacesContext facesContext;

	    @Inject
	    private PessoaRegistration memberPessoa;

	    @Produces
	    @Named
	    private Pessoa newPessoa;
	    
	    @Inject
	    private PessoaRepositorio pr;
	    
	    @Produces
	    @Named
	    private List<Pessoa> listPessoas;
	    
	    @PostConstruct
	    public void initNewPessoa() {
	        newPessoa = new Pessoa();
	        listPessoas = pr.getListPessoas();
	    }

	    public void register() throws Exception {
	        try {
	            memberPessoa.register(newPessoa);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
	            facesContext.addMessage(null, m);
	            initNewPessoa();
	        } catch (Exception e) {
	            String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	        }
	    }

	    private String getRootErrorMessage(Exception e) {
	        // Default to general error message that registration failed.
	        String errorMessage = "Registration failed. See server log for more information";
	        if (e == null) {
	            // This shouldn't happen, but return the default messages
	            return errorMessage;
	        }

	        // Start with the exception and recurse to find the root cause
	        Throwable t = e;
	        while (t != null) {
	            // Get the message from the Throwable class instance
	            errorMessage = t.getLocalizedMessage();
	            t = t.getCause();
	        }
	        // This is the root cause message
	        return errorMessage;
	    }
	    
}
