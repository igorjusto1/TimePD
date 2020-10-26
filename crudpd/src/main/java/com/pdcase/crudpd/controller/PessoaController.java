package com.pdcase.crudpd.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.pdcase.crudpd.model.Cadastro;
import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.service.CadastroService;
import com.pdcase.crudpd.service.PessoaService;
import com.pdcase.crudpd.util.CpfConverter;
import com.pdcase.crudpd.viewmodel.CadastroSelectList;
import com.pdcase.crudpd.viewmodel.PessoaViewModel;

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
	private PessoaViewModel newPessoa;

	// Lista de pessoas na view
	private List<PessoaViewModel> pessoas;

	// Lista de enderecos para cadastro
	private List<CadastroSelectList> enderecos;

	// File para upload csv
	private UploadedFile fileUpload;

	// file para download de csv
	private StreamedContent fileDownload;

	@Inject
	private FacesContext facesContext;

	public PessoaViewModel getNewPessoa() {
		return newPessoa;
	}

	public void setNewPessoa(PessoaViewModel newPessoa) {
		this.newPessoa = newPessoa;
	}

	public List<PessoaViewModel> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaViewModel> pessoas) {
		this.pessoas = pessoas;
	}

	public UploadedFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<CadastroSelectList> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<CadastroSelectList> enderecos) {
		this.enderecos = enderecos;
	}

	@PostConstruct
	public void init() {
		refreshList();
		cleanPessoa();
	}

	public void refreshList() {
		pessoas = pessoaService.getAllPessoas();
		setEnderecos(pessoaService.getAllCadastro());
	}

	public void cleanPessoa() {
		newPessoa = new PessoaViewModel();
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
					newPessoa = new PessoaViewModel();
					newPessoa.setCpf(cpf);
					newPessoa.setNome(spli[0]);
					newPessoa.setSobrenome(spli[1]);
					newPessoa.setNascimento(convertParaDateTime(i, spli[3]));
					i++;

					pessoaService.register(newPessoa);

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

	public void download() {
		StringBuilder linhas = new StringBuilder("Id;Nome;Sobrenome;Cpf;Data de Nascimento");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for (PessoaViewModel p : pessoas) {
			StringBuilder linha = new StringBuilder();

			linha.append(p.getId());
			linha.append(";");
			linha.append(p.getNome());
			linha.append(";");
			linha.append(p.getSobrenome());
			linha.append(";");
			linha.append(CpfConverter.formatCpf(p.getCpf()));
			linha.append(";");
			linha.append(formatter.format(p.getNascimento()));
			linha.append(";\r\n");

			linhas.append(linha);
		}

		InputStream is = new ByteArrayInputStream(linhas.toString().getBytes(Charset.forName("UTF-8")));
		fileDownload = DefaultStreamedContent.builder().name("lista.csv").contentType("text/csv").stream(() -> is)
				.build();

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
