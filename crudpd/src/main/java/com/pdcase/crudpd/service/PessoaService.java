package com.pdcase.crudpd.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.pdcase.crudpd.data.EnderecoRepositorio;
import com.pdcase.crudpd.data.PessoaRepositorio;
import com.pdcase.crudpd.model.Pessoa;
import com.pdcase.crudpd.viewmodel.CadastroSelectList;
import com.pdcase.crudpd.viewmodel.PessoaViewModel;

public class PessoaService {
	@Inject
	private Logger log;

	@Inject
	private PessoaRepositorio pr;
	@Inject
	private EnderecoRepositorio cr;


	public void register(PessoaViewModel pessoa) {
		log.info("Registering " + pessoa.getNome());
		pr.saveOrUpdate(pessoa.toPessoa());
	}
	
	public List<Pessoa> getAllPessoas(){
		return pr.getListPessoas();
	}

	public List<PessoaViewModel> getAllPessoasViewModel(){
		return pr.getListPessoas().stream().map(PessoaViewModel::new).collect(Collectors.toList());
	}
	
	public List<CadastroSelectList> getAllCadastro(){

		return cr.getListCadastro().stream().map(CadastroSelectList::new).collect(Collectors.toList());
	}
	
	public PessoaViewModel edit(int id) {
		return new PessoaViewModel(pr.findById(id));
	}

	public void delete(int id) {
		Pessoa pessoa = pr.findById(id);

		log.info("Apagando " + pessoa.getNome());

		pr.deleteById(id);


	}
}
