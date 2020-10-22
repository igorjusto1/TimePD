package com.pdcase.crudpd.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.PessoaService;

// Named serve pra fazer com que seja um bean gerenciado. Nome padrão é pessoaController pra acesso nas views
@Named
// Escopo das variáveis do controlador.
@RequestScoped
public class PessoaController implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	// Camada de service
	@Inject
	private PessoaService pessoaService;

	// Logger pra erros
	private transient Logger log;

	// Modelo utilizado durante os requests
	private Pessoa newPessoa;

	// Lista de pessoas na view
	private List<Pessoa> pessoas;

	// File para upload csv
	private UploadedFile fileUpload;

	@Inject
	private FacesContext facesContext;



	public Pessoa getNewPessoa() {
		return newPessoa;
	}

	public void setNewPessoa(Pessoa newPessoa) {
		this.newPessoa = newPessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public UploadedFile getFile() {
		return fileUpload;
	}

	public void setFile(UploadedFile file) {
		this.fileUpload = file;
	}

	@PostConstruct
	public void init() {
		pessoas = pessoaService.getAllPessoas();
		newPessoa = new Pessoa();
	}

	// Salva o objeto salvo no request
	public void register() {
		try {
			pessoaService.register(newPessoa);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!",
					"Registro de " + newPessoa.getNome() + " completado com sucesso");
			facesContext.addMessage(null, m);

			init();

		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			log.info(errorMessage);
		}
	}

	// Apaga o objeto passado por id no request
	public void delete(int id) {
		try {
			pessoaService.delete(id);
			init();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Remoção não concluída");
			facesContext.addMessage(null, m);

			log.info(errorMessage);
		}
	}

	// Carrega o objeto passado por id para edição
	public void edit(int id) {
		try {
			newPessoa = pessoaService.edit(id);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição carregada!",
					"Dados de " + newPessoa.getNome() + " carregados para edição com sucesso");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Edição não carregada");
			facesContext.addMessage(null, m);

			log.info(errorMessage);
		}
	}

	public void upload() {
		if (fileUpload != null) {

			byte[] fileContent = fileUpload.getContent();

			InputStream is = new ByteArrayInputStream(fileContent);
			BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
			String temp = null;

			try {
				int i = 1;
				while ((temp = bfReader.readLine()) != null) {
					String[] spli = temp.split(";");

					String cpf = spli[2].replace("-", "");
					cpf = cpf.replace(".", "");
					newPessoa = new Pessoa();
					newPessoa.setCpf(cpf);
					newPessoa.setNome(spli[0]);
					newPessoa.setSobrenome(spli[1]);
					newPessoa.setNascimento(convertParaDateTime(i, spli[3]));
					i++;

					register();

				}
			} catch (IOException e) {
				String errorMessage = getRootErrorMessage(e);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Erro no Upload de arquivo");
				facesContext.addMessage(null, m);
				log.info(errorMessage);
			}
			FacesMessage message = new FacesMessage("Successful", fileUpload.getFileName() + " carregado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * @param i
	 * @param spli
	 */
	private Date convertParaDateTime(int i, String dateValue) {

		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dateValue);
		} catch (ParseException e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
					"Erro na conversão da linha " + i + " do arquivo");
			facesContext.addMessage(null, m);
			log.info(errorMessage);
		}
		return new Date();
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Operação falhou. Veja o log do servidor para mais informações";
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
